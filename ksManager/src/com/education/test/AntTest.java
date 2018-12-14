package com.education.test;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

public class AntTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Project project = new Project();
		project.setBasedir("D:\\myworking\\zbx-qt\\trunk");
		project.setName("zbx-qt");
		project.setDefault("complie");
		
		Path path = new Path(project,"D:\\myworking\\zbx-qt\\trunk\\src");
		Path classPath = new Path(project);
		
		FileSet fs = new FileSet();
		fs.setDir(new File("D:\\myworking\\zbx-qt\\trunk\\WebRoot\\WEB-INF\\lib"));
		fs.appendIncludes(new String[]{"*.jar","*.zip"});
		classPath.addFileset(fs);
		
		FileSet fs2 = new FileSet();
		fs2.setDir(new File("D:\\Java\\apache-tomcat-6.0.33\\lib"));
		fs2.appendIncludes(new String[]{"*.jar","*.zip"});
		classPath.addFileset(fs2);
		
		Target target = new Target();
		target.setName("complie");
		
		Javac javac = new Javac();
		javac.setSrcdir(path);
		javac.setDestdir(new File("D:\\TEMP"));
		javac.setEncoding("gbk");
		javac.setClasspath(classPath);
		javac.setIncludeantruntime(true);
		
		javac.execute();
//		target.addTask(javac);
//		
//		
//		project.addTarget("complie",target);
//		
//		project.executeTarget("complie");
	}

}
