package com.education.test;

import java.io.IOException;
import java.util.Arrays;

import javax.tools.*;


public class CompileTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
//		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();  
//		JavaFileObject file = null;  
//        
//        Iterable options = Arrays.asList("-sourcepath","D:\\myworking\\zbx-qt\\trunk\\src");  
//        Iterable<? extends JavaFileObject> files = Arrays.asList(file);  
//        JavaCompiler.CompilationTask task = javaCompiler.getTask (null, null, null, options, null, files);  
          
        //Boolean result = task.call();  
        //System.out.println(result);  

		//Iterable<String> options = Arrays.asList("-d","D:\\myworking\\zbx-qt\\trunk\\src");
//		JavaCompiler.CompilationTask task = javaCompiler.getTask(null, javaFileManager,
//				null, Arrays.asList("-d","d:/temp"), null, compilationUnits);
//		 // 编译程序  
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();  
        int result = javaCompiler.run(null, null, null, "-encoding", "GBK", "-sourcepath","d:/temp/","D:/myworking/zbx-qt/trunk/src/*.*");  
        System.out.println( result == 0 ? "恭喜编译成功" : "对不起编译失败");  
          
        
        // 运行程序  
//        Runtime run = Runtime.getRuntime();  
//        Process process = run.exec("java -cp ./temp temp/com/Hello");  
//        InputStream in = process.getInputStream();  
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
//        String info  = "";  
//        while ((info = reader.readLine()) != null) {  
//            System.out.println(info);  
//                  
//        }  
	}

}
