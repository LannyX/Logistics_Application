package logistic_application;


//just call this class client for now, we can always change name later.
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FacilityMgr FMgr = new FacilityMgr();
		FMgr.GetInstance();
		FMgr.PrintTheReport();
		
		
	}

}
