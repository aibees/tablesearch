package tableSearch;

import tableSearch.view.*;

public class Execute {
	
	LoginView loginView;
	MainView  mainView;
	
	public static void main(String[] args) {

		Execute execute = new Execute();
		execute.loginView = new LoginView();
		execute.loginView.setMain(execute);
		
	}
	
    // 메인 화면으로 전환
    public void showFrameTest(){
        loginView.dispose(); 
        this.mainView = new MainView(); 
    }
}
