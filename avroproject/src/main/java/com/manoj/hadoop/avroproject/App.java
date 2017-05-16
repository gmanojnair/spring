package com.manoj.hadoop.avroproject;

import java.io.Serializable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.cloudera.crunch.Pipeline;
import com.cloudera.crunch.impl.mr.MRPipeline;

/**
 * Hello world!
 *
 */
public class App extends Configured implements Tool, Serializable {

	@Option(required = true, name = "-dbUrl", usage = "Jdbc database url to export to")
	private String jdbcUrl;
	@Option(required = true, name = "-dbUser", usage = "Jdbc database user")
	private String jdbcUser;

	@Option(required = true, name = "-dbPwd", usage = "Jdbc database password")
	private String jdbcPassword;

	private static final String TRACKER_HOST = "tracker.host";
	private static final String HDFS_HOST = "hdfs.host";
	private static final long serialVersionUID = 7413362117779053368L;

	public static void main(String[] args) throws Exception {
		System.out.println("Hello World!");
		long start = System.currentTimeMillis();
		Configuration config = new Configuration();
		ToolRunner.run(config, new App(), args);
		System.out.println("Time taken: "
				+ (System.currentTimeMillis() - start) / 1000 + "s");
	}

	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		if (parseArguments(arg0)) {

			// set db
   
			// run map reduce pipeline
			runPipeline();
		}
		return 0;
	}

	private void setHadoopConfiguration() {
		getConf().setInt("mapred.job.reuse.jvm.num.tasks", -1);
		// Do not allow concurrent or multiple executions of tasks
		getConf().setInt("mapred.map.max.attempts", 1);
		getConf().setInt("mapred.reduce.max.attempts", 1);
		getConf()
				.setBoolean("mapred.reduce.tasks.speculative.execution", false);

		// Possibly change hadoop cluster
		String hdfsHost = System.getProperty(HDFS_HOST);
		getConf().set("fs.defaultFS", hdfsHost);

		String trackerHost = System.getProperty(TRACKER_HOST);
		getConf().set("mapred.job.tracker", trackerHost);

	}

	private boolean parseArguments(final String[] args) {
		final CmdLineParser parser = new CmdLineParser(this);

		setHadoopConfiguration();

		try {
			parser.parseArgument(args);

		} catch (final CmdLineException e) {
			parser.printUsage(System.err);
			GenericOptionsParser.printGenericCommandUsage(System.err);
			return false;
		}
		return true;
	}
	
	 private void runPipeline() {
	        Pipeline pipeline = new MRPipeline(App.class, getConf());
	        try {
	         
	        } finally {
	            pipeline.done();
	        }
	    }

}
