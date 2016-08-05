package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.apache.log4j.Logger;

import property.PropertyUtil;

import bo.AddEmployeeBO;

import exception.TagsBusinessException;

public class AddEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final Logger LOG = Logger.getLogger(AddEmployeeController.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("message", null);
		vo.EmployeeVO addEmployeeVo = new vo.EmployeeVO();
		String Employee_Id = request.getParameter("Employee_Id");
		String Employee_Name = request.getParameter("Employee_Name");
		String DOJ = request.getParameter("DOJ");
		String Grade = request.getParameter("Grade");
		boolean validateFlag = true;
		LOG.info("Entering Job");
		try {
			if (!((Employee_Id.isEmpty() || Employee_Id == ""))
					&& (!(Employee_Name.isEmpty() || Employee_Name == ""))
					&& ((Integer.valueOf(Grade) != -1))
					&& (!(DOJ.isEmpty() || DOJ == ""))) {
				addEmployeeVo.setEmployeeId(Employee_Id);
				addEmployeeVo.setEmployeeName(Employee_Name);
				addEmployeeVo.setGrade(Integer.valueOf(Grade));
				addEmployeeVo.setDOJ(DOJ);
				AddEmployeeBO employeeDetailBo = new AddEmployeeBO();
				if (!employeeDetailBo.checkEmployeeId(addEmployeeVo)) {
					validateFlag = false;
					LOG.info(validateFlag);
				}

				if (!employeeDetailBo.checkEmployeeName(addEmployeeVo)) {
					validateFlag = false;
				}

				if (!employeeDetailBo.checkDOJ(addEmployeeVo)) {
					validateFlag = false;
				}

				if (validateFlag) {
					dao.EmployeeDAO insertDao = new dao.EmployeeDAO();
					insertDao.insertEmployeeDetails(addEmployeeVo);
					request.setAttribute("message",
							PropertyUtil.getMessage("105"));
					
					LOG.info("Inside Add employee controller validate.");
				}
			} else {
				LOG.info("Inside Add employee controller all fields are mandatory.");
				request.setAttribute("message", PropertyUtil.getMessage("106"));
				

			}

		} catch (TagsBusinessException e) {
			LOG.error(e);
			request.setAttribute("message", e.getMessage());
			
		} catch (SQLException e) {
			
			LOG.error("Error in SQL. Reason of failure :: " + e.getMessage());
			request.setAttribute("message", e.getMessage());
			
		} catch (Exception e) {
			LOG.error("Error while login. Reason of failure :: "
					+ e.getMessage());
			request.setAttribute("message", e.getMessage());
			
		}
		finally{
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("AddEmployee.jsp");
	dispatcher.forward(request, response);
		}
	}

}
