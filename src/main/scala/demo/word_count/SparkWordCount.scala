import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object SparkWordCount {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkWordCount")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("hdfs://localhost:9000/input")
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile("hdfs://localhost:9000/output")
  }

}