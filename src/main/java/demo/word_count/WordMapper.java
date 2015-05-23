package demo.word_count;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

    private static final Log logger = LogFactory.getLog(WordCount.class);

    private final static IntWritable ONE = new IntWritable(1);

    private Text word = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        logger.info("key: " + key);
        logger.info("value: " + value);

        final StringTokenizer tokenizer = new StringTokenizer(value.toString());
        while (tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken());
            context.write(word, ONE);
        }
    }
}
