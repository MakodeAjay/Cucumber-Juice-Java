package Utils;

import com.aventstack.extentreports.ExtentTest;

/**
 * ReporterFactory class
 * @author AjayMakode
 *
 */
public class ReporterFactory {
	

		private ReporterFactory() {	}
	
		private static ReporterFactory ReporterFactoryInstance = new ReporterFactory();
		
		public static ReporterFactory getInstance() 
		{
			return ReporterFactoryInstance;
		}

		ThreadLocal<ExtentTest> extentTestList = new ThreadLocal<ExtentTest>();
		
		public ExtentTest getExtentTest()
		{
			return extentTestList.get();
		}
		
		public void setExtentTestList(ExtentTest extentTest)
		{
			extentTestList.set(extentTest);
		}
		
		public void finisheExtentTest()
		{
			extentTestList.remove();
		}

}
