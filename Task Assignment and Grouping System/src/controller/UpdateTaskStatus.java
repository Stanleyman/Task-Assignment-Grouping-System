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

import vo.AssignOwnerVO;
import vo.AssignTaskVO;
import vo.TaskUpdateVO;

import bo.AssignTaskBO;

public class UpdateTaskStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(UpdateTaskStatus.class);
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String groupId = request.getParameter("GroupNames");
		String employeeId = request.getParameter("EmployeeNames");
		String taskId = request.getParameter("TaskDetails");
		String taskUpdates = request.getParameter("TaskUpdate");
		TaskUpdateVO vo = new TaskUpdateVO();
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
						LOG.info("Inside update task status controller validate.");
					} else {
						request.setAttribute("message", PropertyUtil.getMessage("204"));
					}
					
				}
			} else if (request.getParameter("getTasks") != null) {
				if (request.getParameter("getTasks").equals("Get Tasks")) {
			
				
				if (((groupId != null) && Integer.valueOf(groupId) != -1)
						&& ((employeeId != null) && Integer.valueOf(employeeId) != -1)) {
					
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

				} else {
					request.setAttribute("message",PropertyUtil.getMessage("401"));
					LOG.info("inside update task status controller..select the field");
				}
				
			}
			}
			
			else if (Integer.valueOf(taskId) != -1
					&& Integer.valueOf(employeeId) != -1
					&& Integer.valueOf(taskId) != -1
					&& (!(taskUpdates.isEmpty() || taskUpdates == ""))) {
				
				AssignTaskBO bo = new AssignTaskBO();
				vo.setEmployeeId(Integer.valueOf(employeeId));
				vo.setGroupId(Integer.valueOf(groupId));
				vo.setTaskId(Integer.valueOf(taskId));
				vo.setTaskUpdates(taskUpdates);
				boolean flag = true;
				if (!bo.checkTaskUpdate(vo)) {
					flag = false;
				}
				if (flag) {TaskAssignDAO taskAssignDAO=new TaskAssignDAO();
				
				    taskAssignDAO.insertTaskUpdate(vo);
					request.setAttribute("message",PropertyUtil.getMessage("402"));
					
				}
			} else {
				
				request.setAttribute("message",PropertyUtil.getMessage("106"));
				
			}
		} catch (Exception e) {
			LOG.error("Error in task update. Reason of failure :: " + e.getMessage());
			request.setAttribute("message", e.getMessage());
			request.setAttribute("message", e.getMessage());
			
		}
		finally{
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("UpdateTaskStatus.jsp");
	dispatcher.forward(request, response);
		}
	}
}
