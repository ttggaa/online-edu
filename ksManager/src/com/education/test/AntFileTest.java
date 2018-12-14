package com.education.test;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class AntFileTest {

	public static void main(String[] args) {
		AntFileTest antFile = new AntFileTest();
		String antFilePath = "D:/myworking/zbx-qt/trunk/build.xml";
		try {
			antFile.executeAntFile("arg", antFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void executeAntFile(String parater, String antFilePath) throws Exception {
		try {
			File buildFile = new File(antFilePath);
			Project ant_Project = new Project();
			ant_Project.init();
			ant_Project.setProperty("zipfilename", parater);
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			helper.parse(ant_Project, buildFile);
			ant_Project.executeTarget(ant_Project.getDefaultTarget());
			ant_Project.fireBuildFinished(null);
			
		} catch (Exception e) {
			throw e;
		}
	}

}
