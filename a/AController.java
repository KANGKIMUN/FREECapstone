package a;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AController
 */
@WebServlet("/AController")
public class AController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	   private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		      HttpSession session = request.getSession();
		      String user_id = checklogin(session);
		      if(user_id != null) {
		         String com = parse(request);
		         
		         Command c = new Command();
		         
		         // 커맨드별로 severlet생성
		         if(com.equals("/getCardsByUserAndType.do")) {
		            //파라미터  사용
		            String cardType = request.getParameter("cardType");
		            c.getCardsByUserAndType(user_id, cardType);
		            
		            
		        
		            
		         }
		         
		      }
		   }
		   
		   private String parse(HttpServletRequest request){
		      String uri = request.getRequestURI();
		      String conPath = request.getContextPath();
		      String com = uri.substring(conPath.length());
		      
		      return com;   
		   }
		   
		   
		   protected String checklogin(HttpSession session) {
		      if (session != null && session.getAttribute("user") != null) {
		         String userId = (String) session.getAttribute("user");
		            return userId;
		        } else {
		            return null;
		        }
		   }

}
