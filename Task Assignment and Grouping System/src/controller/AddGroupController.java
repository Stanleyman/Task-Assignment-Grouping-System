package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import property.PropertyUtil;

import dao.GroupDAO;

import exception.TagsBusinessException;

import vo.AddGroupNameVO;

import bo.AddGroupBO;


public class AddGroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final Logger LOG = Logger.getLogger(AddGroupController.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String GroupName=request.getParameter("GroupName");
		boolean validateFlag=true;
		final AddGroupNameVO addGroupVo=new AddGroupNameVO();
		LOG.info("Entering GROUP NAME");
		try {
			if(!(GroupName.equals("")))
		{
				addGroupVo.setGroupName(GroupName);
				final AddGroupBO addGroupBo=new AddGroupBO();
				if(!addGroupBo.checkGroupName(addGroupVo))
				{
				
					validateFlag=false;
						
				}
				
				if(validateFlag)
				{
				
					final GroupDAO insertGroupDao=new GroupDAO();
					insertGroupDao.insertGroupName(addGroupVo);
					request.setAttribute("message",PropertyUtil.getMessage("202"));
					
					LOG.info("Inside Add GROUP controller validate. Added sucessfully");
				}
			
			
			
		}
		else
		{
			LOG.error("Inside Add group controller validate.group name mandatory");
			request.setAttribute("message",PropertyUtil.getMessage("203"));
			
		}
		} catch (TagsBusinessException e) {
			
			request.setAttribute("message",e.getMessage());
			
		}catch(Exception e)
		{
			LOG.error("Error while login in group added. Reason of failure :: " + e.getMessage());
			request.setAttribute("message",e.getMessage());
			
		}
		finally
		{
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("AddGroup.jsp");
			dispatcher.forward(request, response);
		}
	}

}
