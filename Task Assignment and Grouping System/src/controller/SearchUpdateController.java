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
import vo.AssignTaskVO;
import vo.SearchReportVO;
import vo.TaskUpdateVO;
//import bo.AssignTaskBO;
import dao.EmployeeDAO;
import dao.SearchUpdateDAO;
import dao.TaskAssignDAO;

public class SearchUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(SearchUpdateController.class);
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String groupId = request.getParameter("GroupNames");
		
		final String employeeId = request.getParameter("EmployeeNames");
		final String taskId = request.getParameter("TaskDetails");
		
		TaskUpdateVO taskUpdateVo = new TaskUpdateVO();
		try {

			if (request.getParameter("getEmployees") != null) {
				
				if ((request.getParameter("getEmployees")
						.equals("Get Employees"))) {
					
					if ((groupId != null) && Integer.valueOf(groupId) != -1) {

						EmployeeDAO empNameDao = new EmployeeDAO();
						ArrayList<AssignOwnerVO> employeeNames = new ArrayList<AssignOwnerVO>();

						AssignOwnerVO assignOwnerVo = new AssignOwnerVO();

						assignOwnerVo.setGroupId(Integer.valueOf(groupId));
						employeeNames = empNameDao
								.getEmployeeNamesInGivenGroup(assignOwnerVo);
						request.setAttribute("status", "1");
						request.setAttribute("groupId", groupId);
						request.setAttribute("employeeNames", employeeNames);
						LOG.info("Inside search update controller validate.");
					} else {
						request.setAttribute("message",PropertyUtil.getMessage("204"));
					}

				}
			}
			if ((request.getParameter("getTasks") != null)) {
								if (request.getParameter("getTasks").equals("Get Tasks")) {

					
					if (((groupId != null) && Integer.valueOf(groupId) != -1)
							&& ((employeeId != null) && Integer
									.valueOf(employeeId) != -1)) {
					
						TaskAssignDAO taskAssignDao = new TaskAssignDAO();
						ArrayList<AssignTaskVO> taskDetails = new ArrayList<AssignTaskVO>();
						AssignTaskVO assignTaskVo = new AssignTaskVO();
						assignTaskVo.setEmployeeId(Integer.valueOf(employeeId));
						assignTaskVo.setGroupId(Integer.valueOf(groupId));
						taskDetails = taskAssignDao
								.getTaskByGroupEmployee((assignTaskVo));
						request.setAttribute("status", "2");
						request.setAttribute("groupId", groupId);

						EmployeeDAO empNameDao = new EmployeeDAO();
						ArrayList<AssignOwnerVO> employeeNames = new ArrayList<AssignOwnerVO>();

						AssignOwnerVO assignOwnerVo = new AssignOwnerVO();

						assignOwnerVo.setGroupId(Integer.valueOf(groupId));
						employeeNames = empNameDao
								.getEmployeeNamesInGivenGroup(assignOwnerVo);
						request.setAttribute("employeeId", employeeId);
						request.setAttribute("taskDetails", taskDetails);
						request.setAttribute("employeeNames", employeeNames);

					}

					if (Integer.valueOf(employeeId) == -1
							&& Integer.valueOf(groupId) != -1) {
						
						if (Integer.valueOf(groupId) != -1
								&& request.getParameter("getTasks").equals(
										"Get Tasks")) {
								
							taskUpdateVo.setGroupId(Integer.valueOf(groupId));
							SearchUpdateDAO searchUpdateDao = new SearchUpdateDAO();
							ArrayList<AssignTaskVO> taskListByGroup = new ArrayList<AssignTaskVO>();
							taskListByGroup = searchUpdateDao
									.getTaskByGroup(taskUpdateVo);
							request.setAttribute("taskDetails", taskListByGroup);
							request.setAttribute("status", "3");
							request.setAttribute("groupId", groupId);
							
						}

					}

					if ((employeeId == null || Integer.valueOf(employeeId) == -1)
							&& (groupId == null || Integer.valueOf(groupId) == -1)) {
						request.setAttribute("message",PropertyUtil.getMessage("401"));
					}

				}
			}

		 if (request.getParameter("viewReport")!=null) {
			 
			
			if(Integer.valueOf(groupId)!=-1)
			{
				if(Integer.valueOf(employeeId)!=-1 && Integer.valueOf(taskId)!=-1)
				{
					
					taskUpdateVo.setEmployeeId(Integer.valueOf(employeeId));
					taskUpdateVo.setTaskId(Integer.valueOf(taskId));
					taskUpdateVo.setGroupId(Integer.valueOf(groupId));
					SearchUpdateDAO searchUpdateDao=new SearchUpdateDAO();
					ArrayList<SearchReportVO> reportEmpGrpTask=searchUpdateDao.getReportByAll(taskUpdateVo);
					request.setAttribute("taskDetails",reportEmpGrpTask);
					request.setAttribute("groupId", groupId);
					request.setAttribute("employeeId",employeeId);
					
					request.setAttribute("taskId", taskId);
					request.setAttribute("report", "2");
					
					
					
				}
				else if(Integer.valueOf(employeeId)!=-1 || Integer.valueOf(taskId)!=-1)
				{
					if(Integer.valueOf(employeeId)!=-1)
					{
						taskUpdateVo.setEmployeeId(Integer.valueOf(employeeId));
						taskUpdateVo.setGroupId(Integer.valueOf(groupId));
						SearchUpdateDAO searchUpdateDao=new SearchUpdateDAO();
						ArrayList<SearchReportVO> reportEmpGrpTask=searchUpdateDao.getReportByGrpEmp(taskUpdateVo);
						request.setAttribute("taskDetails",reportEmpGrpTask);
						request.setAttribute("groupId", groupId);
						request.setAttribute("employeeId",employeeId);
						request.setAttribute("report", "1");
						
						
					}
					if(Integer.valueOf(taskId)!=-1)
					{
						
						taskUpdateVo.setTaskId(Integer.valueOf(taskId));
						taskUpdateVo.setGroupId(Integer.valueOf(groupId));
						SearchUpdateDAO searchUpdateDao=new SearchUpdateDAO();
						ArrayList<SearchReportVO> reportEmpGrpTask=searchUpdateDao.getReportByGrpTask(taskUpdateVo);
						request.setAttribute("taskDetails",reportEmpGrpTask);
						request.setAttribute("groupId", groupId);
						request.setAttribute("taskId", taskId);
						request.setAttribute("report", "2");
						
						
					}
					
				}
				else
				{
					taskUpdateVo.setGroupId(Integer.valueOf(groupId));
					SearchUpdateDAO searchUpdateDao=new SearchUpdateDAO();
					ArrayList<SearchReportVO> reportGrp=searchUpdateDao.getReportByGrp(taskUpdateVo);
					request.setAttribute("taskDetails",reportGrp);
					request.setAttribute("groupId", groupId);
					request.setAttribute("report", "1");
					
					
				}
			} 
			 else {
				
				request.setAttribute("message", PropertyUtil.getMessage("203"));

			}
		 }
		} catch (Exception e) {
			LOG.error("Error. Reason of failure :: " + e.getMessage());
			request.setAttribute("message", e.getMessage());

		} finally {
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("SearchReport.jsp");
			dispatcher.forward(request, response);
		}
	}
}
