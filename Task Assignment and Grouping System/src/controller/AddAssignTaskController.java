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

import dao.EmployeeDAO;
import dao.TaskAssignDAO;
import bo.AssignTaskBO;
import vo.AssignOwnerVO;
import vo.AssignTaskVO;


public class AddAssignTaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(AddAssignTaskController.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ETA=request.getParameter("ETA");
		String taskDetail=request.getParameter("TaskDetail");
		String groupId=request.getParameter("GroupNames");
		String employeeId=request.getParameter("EmployeeNames");
		AssignTaskVO assignTaskVo =new AssignTaskVO();
		try
		{	
			
if (request.getParameter("getEmployees") != null ) {
				
				if ((groupId != null) && Integer.valueOf(groupId) != -1) {
						
					EmployeeDAO empNameDao = new EmployeeDAO();
					ArrayList<AssignOwnerVO> employeeNames = new ArrayList<AssignOwnerVO>();

					AssignOwnerVO vo = new AssignOwnerVO();

					vo.setGroupId(Integer.valueOf(groupId));
					employeeNames = empNameDao.getEmployeeNamesInGivenGroup(vo);
					request.setAttribute("status", "1");
					request.setAttribute("groupId", groupId);
					request.setAttribute("employeeNames", employeeNames);

					LOG.info("Inside Add assign task controller validate.");
				}
				else{
					request.setAttribute("message",PropertyUtil.getMessage("204"));
				}
				
				
			}	
			
			
		else if((!(ETA.isEmpty()||ETA==""))&& (!(taskDetail.isEmpty()||taskDetail==""))&&((groupId!="select"))&&(employeeId!="select"))
		{
			
			AssignTaskBO bo=new AssignTaskBO();
			boolean flag=true;
			assignTaskVo.setETA(ETA);
			assignTaskVo.setTaskDetail(taskDetail);
			assignTaskVo.setGroupId(Integer.valueOf(groupId));
			assignTaskVo.setEmployeeId(Integer.valueOf(employeeId));
			if(!bo.checkTaskDetail(assignTaskVo))
			{
			
				flag=false;
			}
			if(!bo.checkETA(assignTaskVo))
			{
				
				flag=false;
			}
			
			if(flag)
			{
	
				TaskAssignDAO dao=new TaskAssignDAO();
				dao.insertTask(assignTaskVo);
				request.setAttribute("message",PropertyUtil.getMessage("304"));
				
			}
			
		}
		else
		{
			
			request.setAttribute("message",PropertyUtil.getMessage("106"));
			
		}
		}
		catch(Exception e)
		{
			LOG.error("Error.. Reason of failure :: "
					+ e.getMessage());
			request.setAttribute("message",e.getMessage() );
			
		}
		finally{
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("AddAssignTask.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
