package demo.word_count;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCount {

    private static final Log logger = LogFactory.getLog(WordCount.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        logger.info("Started");

        final Configuration configuration = new Configuration();
        final Job job = Job.getInstance(configuration, "WordCount");

        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        final Path inputPath = new Path(args[0]);
        final Path outputPath = new Path(args[1]);

        logger.info("Input Path = " + inputPath);
        logger.info("Output Path = " + outputPath);

        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        if (job.waitForCompletion(true)) {
            logger.info("Success");
            System.exit(0);
        } else {
            logger.info("Failed");
            System.exit(1);
        }
    }

}
