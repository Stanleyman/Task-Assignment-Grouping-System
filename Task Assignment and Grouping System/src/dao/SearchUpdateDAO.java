package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBUtils;
import utils.SQLQueries;
import vo.AssignTaskVO;
import vo.SearchReportVO;
import vo.TaskUpdateVO;

public class SearchUpdateDAO {

	public ArrayList<AssignTaskVO> getTaskByGroup(TaskUpdateVO taskUpdateVo) throws ClassNotFoundException, SQLException {
		Connection connection=DBUtils.createConnection();
		
		ArrayList<AssignTaskVO> taskDetailList= new ArrayList<AssignTaskVO>();
		try
		{
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.GETTASKBYGROUP);
			stmt.setInt(1, taskUpdateVo.getGroupId());
			ResultSet taskDetail=stmt.executeQuery();
			while(taskDetail.next())
			{
				AssignTaskVO assignTaskVo=new AssignTaskVO();
				assignTaskVo.setTaskId(taskDetail.getInt(1));
				assignTaskVo.setTaskDetail(taskDetail.getString(2));
				taskDetailList.add(assignTaskVo);
				
			}
		}
		finally
		{
			connection.close();
		}
		
		return taskDetailList;
	}

	public ArrayList<SearchReportVO> getReportByAll(TaskUpdateVO vo) throws ClassNotFoundException, SQLException {
		
		Connection connection=DBUtils.createConnection();
		ArrayList<SearchReportVO> taskDetails=new ArrayList<SearchReportVO>();
		String taskName=null;
		String empName=null;
		String groupName=null;
		try
		{
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.GETREPORTBYALL);
			stmt.setInt(1, vo.getTaskId());
			stmt.setInt(2, vo.getEmployeeId());
			stmt.setInt(3, vo.getGroupId());
			
			ResultSet result=stmt.executeQuery();
			PreparedStatement stmtTask=connection.prepareStatement(SQLQueries.GETTASKNAME);
			stmtTask.setInt(1, vo.getTaskId());
			ResultSet resTask=stmtTask.executeQuery();
			while(resTask.next())
			{
				taskName=resTask.getString(1);
				
			}
			PreparedStatement stmtEmployee=connection.prepareStatement(SQLQueries.GETEMPLOYEE);
			stmtEmployee.setInt(1, vo.getEmployeeId());
			ResultSet resEmp=stmtEmployee.executeQuery();
			while(resEmp.next())
			{
				empName=resEmp.getString(1);
				
			}
			PreparedStatement stmtGroup=connection.prepareStatement(SQLQueries.GETGROUP);
			stmtGroup.setInt(1, vo.getGroupId());
			ResultSet resGroup=stmtGroup.executeQuery();
			while(resGroup.next())
			{
				groupName=resGroup.getString(1);
				
			}
			while(result.next())
			{
				SearchReportVO searchUpdateVo=new SearchReportVO();
				searchUpdateVo.setCreatedDate(result.getString(3));
				searchUpdateVo.setEmployeeId(vo.getEmployeeId());
				searchUpdateVo.setGroupId(vo.getGroupId());
				searchUpdateVo.setTaskId(vo.getTaskId());
				searchUpdateVo.setTaskUpdateId(result.getInt(1));
				searchUpdateVo.setTaskUpdate(result.getString(2));
				searchUpdateVo.setTaskDetail(taskName);
				searchUpdateVo.setEmployeeName(empName);
				searchUpdateVo.setGroupName(groupName);
				
				taskDetails.add(searchUpdateVo);
			}
		}
		finally
		{
			connection.close();
		}
		return taskDetails;
	}

	public ArrayList<SearchReportVO> getReportByGrpEmp(TaskUpdateVO vo) throws ClassNotFoundException, SQLException {
		
		Connection connection=DBUtils.createConnection();
		ArrayList<SearchReportVO> taskDetails=new ArrayList<SearchReportVO>();
		String empName=null;
		String groupName=null;
		String[] taskList=null;
		ArrayList<String> taskListArray=new ArrayList<String>();
		try
		{
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.GETREPORTBYGROUPEMP);
			
			stmt.setInt(1, vo.getEmployeeId());
			stmt.setInt(2, vo.getGroupId());
			PreparedStatement stmtEmployee=connection.prepareStatement(SQLQueries.GETEMPLOYEE);
			stmtEmployee.setInt(1, vo.getEmployeeId());
			ResultSet resEmp=stmtEmployee.executeQuery();
			while(resEmp.next())
			{
				empName=resEmp.getString(1);
			}
			PreparedStatement stmtGroup=connection.prepareStatement(SQLQueries.GETGROUP);
			stmtGroup.setInt(1, vo.getGroupId());
			ResultSet resGroup=stmtGroup.executeQuery();
			while(resGroup.next())
			{
				groupName=resGroup.getString(1);
			}
			PreparedStatement stmttaskList=connection.prepareStatement(SQLQueries.GETTASKLIST);
			stmttaskList.setInt(1, vo.getGroupId());
			ResultSet resTaskList=stmttaskList.executeQuery();
			while(resTaskList.next())
			{
			taskListArray.add(resTaskList.getString(1));
			}
			int i=0;
			ResultSet result=stmt.executeQuery();
			while(result.next())
			{
				SearchReportVO searchUpdateVo=new SearchReportVO();
				searchUpdateVo.setCreatedDate(result.getString(3));
				searchUpdateVo.setEmployeeId(vo.getEmployeeId());
				searchUpdateVo.setGroupId(vo.getGroupId());
				searchUpdateVo.setTaskId(result.getInt(4));
				searchUpdateVo.setTaskUpdateId(result.getInt(1));
				searchUpdateVo.setTaskUpdate(result.getString(2));
				searchUpdateVo.setEmployeeName(empName);
				searchUpdateVo.setGroupName(groupName);
				searchUpdateVo.setTaskDetail(taskListArray.get(i));
				taskDetails.add(searchUpdateVo);
				i++;
			}
		}
		finally
		{
			connection.close();
		}
		return taskDetails;
		
	}

	public ArrayList<SearchReportVO> getReportByGrpTask(TaskUpdateVO vo) throws ClassNotFoundException, SQLException {
		
		Connection connection=DBUtils.createConnection();
		ArrayList<SearchReportVO> taskDetails=new ArrayList<SearchReportVO>();
		String taskName=null;
		String groupName=null;
		ArrayList<String> employeeListArray=new ArrayList<String>();
		try
		{
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.GETREPORTBYGROUPTASK);
			stmt.setInt(1, vo.getTaskId());
			stmt.setInt(2, vo.getGroupId());
			PreparedStatement stmtTask=connection.prepareStatement(SQLQueries.GETTASKNAME);
			stmtTask.setInt(1, vo.getTaskId());
			ResultSet resTask=stmtTask.executeQuery();
			
			while(resTask.next())
			{
				taskName=resTask.getString(1);
			}
			
			PreparedStatement stmtGroup=connection.prepareStatement(SQLQueries.GETGROUP);
			stmtGroup.setInt(1, vo.getGroupId());
			ResultSet resGroup=stmtGroup.executeQuery();
			while(resGroup.next())
			{
				groupName=resGroup.getString(1);
			}
			
			PreparedStatement stmtEmployeeList=connection.prepareStatement(SQLQueries.GETEMPLOYEELIST);
			stmtEmployeeList.setInt(1, vo.getGroupId());
			ResultSet resEmpList=stmtEmployeeList.executeQuery();
			
			while(resEmpList.next())
			{
				employeeListArray.add(resEmpList.getString(1));
			}
			
			ResultSet result=stmt.executeQuery();
			int i=0;
			while(result.next())
			{
				SearchReportVO searchUpdateVo=new SearchReportVO();
				searchUpdateVo.setCreatedDate(result.getString(3));
				searchUpdateVo.setEmployeeId(result.getInt(4));
				searchUpdateVo.setGroupId(vo.getGroupId());
				searchUpdateVo.setTaskId(vo.getTaskId());
				searchUpdateVo.setTaskUpdateId(result.getInt(1));
				searchUpdateVo.setTaskUpdate(result.getString(2));
				searchUpdateVo.setEmployeeName(employeeListArray.get(i));
				searchUpdateVo.setGroupName(groupName);
				searchUpdateVo.setTaskDetail(taskName);
				taskDetails.add(searchUpdateVo);
				i++;
			}
		}
		finally
		{
			connection.close();
		}
		
		return taskDetails;
	}

	public ArrayList<SearchReportVO> getReportByGrp(TaskUpdateVO vo) throws ClassNotFoundException, SQLException {
		Connection connection=DBUtils.createConnection();
		ArrayList<SearchReportVO> taskDetails=new ArrayList<SearchReportVO>();
		ArrayList<String> taskListArray=new ArrayList<String>();
		ArrayList<String> employeeListArray=new ArrayList<String>();
		String groupName=null;
		try
		{
			PreparedStatement stmtEmployeeList=connection.prepareStatement(SQLQueries.GETEMPLOYEELIST);
			stmtEmployeeList.setInt(1, vo.getGroupId());
			ResultSet resEmpList=stmtEmployeeList.executeQuery();
			
			while(resEmpList.next())
			{
				employeeListArray.add(resEmpList.getString(1));
			}
			
			PreparedStatement stmttaskList=connection.prepareStatement(SQLQueries.GETTASKLIST);
			stmttaskList.setInt(1, vo.getGroupId());
			ResultSet resTaskList=stmttaskList.executeQuery();
			while(resTaskList.next())
			{
			taskListArray.add(resTaskList.getString(1));
			}
			PreparedStatement stmtGroup=connection.prepareStatement(SQLQueries.GETGROUP);
			stmtGroup.setInt(1, vo.getGroupId());
			ResultSet resGroup=stmtGroup.executeQuery();
			while(resGroup.next())
			{
				groupName=resGroup.getString(1);
			}
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.GETREPORTBYGROUP);
			stmt.setInt(1, vo.getGroupId());
			ResultSet result=stmt.executeQuery();
			int i=0;
			while(result.next())
			{
				SearchReportVO searchUpdateVo=new SearchReportVO();
				searchUpdateVo.setCreatedDate(result.getString(3));
				searchUpdateVo.setEmployeeId(result.getInt(4));
				searchUpdateVo.setGroupId(vo.getGroupId());
				searchUpdateVo.setTaskId(vo.getTaskId());
				searchUpdateVo.setTaskUpdateId(result.getInt(1));
				searchUpdateVo.setTaskUpdate(result.getString(2));
				searchUpdateVo.setEmployeeName(employeeListArray.get(i));
				searchUpdateVo.setGroupName(groupName);
				searchUpdateVo.setTaskDetail(taskListArray.get(i));
				taskDetails.add(searchUpdateVo);
				i++;
			}
		}
		finally
		{
			connection.close();
		}
		
		return taskDetails;
	}
	
	
	
}
