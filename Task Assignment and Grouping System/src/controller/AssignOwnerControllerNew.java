package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import property.PropertyUtil;

import vo.AssignOwnerVO;
import dao.EmployeeDAO;
import dao.GroupDAO;

public class AssignOwnerControllerNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(AssignOwnerControllerNew.class);
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String groupId = request.getParameter("GroupNames");
			String employeeId = request.getParameter("EmployeeNames");
		
			if (request.getParameter("getEmployees") != null) {
				
				if ((groupId != null) && Integer.valueOf(groupId) != -1) {
						
					EmployeeDAO empNameDao = new EmployeeDAO();
					ArrayList<AssignOwnerVO> employeeNames = new ArrayList<AssignOwnerVO>();

					AssignOwnerVO vo = new AssignOwnerVO();

					vo.setGroupId(Integer.valueOf(groupId));
					employeeNames = empNameDao.getEmployeeNames(groupId);
					request.setAttribute("status", "1");
					request.setAttribute("groupId", groupId);
					request.setAttribute("employeeNames", employeeNames);
					LOG.info("Inside assign owner controller validate.");
					
				}
				else{
					request.setAttribute("message", PropertyUtil.getMessage("204"));
				}
				
				
			} else if (((employeeId != null || Integer.valueOf(employeeId) != -1))
					&& (groupId != null || Integer.valueOf(employeeId) != -1)) {
				
				String ownerStatus = request.getParameter("Owner");
				String owner = "";
				AssignOwnerVO assignOwnerVo = new AssignOwnerVO();
				assignOwnerVo.setEmployeeId(Integer.parseInt(employeeId));
				assignOwnerVo.setGroupId(Integer.parseInt(groupId));
			
				if (ownerStatus == null) {
					assignOwnerVo.setOwner(false);
					
				} else {
					assignOwnerVo.setOwner(true);
					
				}
				GroupDAO assignOwnerDao = new GroupDAO();
				EmployeeDAO assignEmployeeDao = new EmployeeDAO();
				
				if (assignEmployeeDao.checkFirstEmployeeDAO(assignOwnerVo) == 0) {
					assignOwnerVo.setOwner(true);
					owner = "(ByDefault first member in the group is owner..)";
				}
			
				assignOwnerDao.addEmployeeToGroup(assignOwnerVo);
				request.setAttribute("message", PropertyUtil.getMessage("105")
						+ owner);
				
				
				LOG.info("Assign owner successfully.inside assign owner controller..");

			} else if (((Integer.valueOf(employeeId) == -1) && employeeId != null)
					&& ((Integer.valueOf(groupId) != -1) || groupId != null)) {

				request.setAttribute("message",PropertyUtil.getMessage("106"));
				
				
			}
			

		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			

		}
		finally{
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("AssignOwnerNew.jsp");
	dispatcher.forward(request, response);
		}

	}

}
