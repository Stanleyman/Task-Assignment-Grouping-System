package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;



import utils.DBUtils;
import utils.SQLQueries;

import vo.AssignTaskVO;
import vo.TaskUpdateVO;

public class TaskAssignDAO {

	public void insertTask(AssignTaskVO vo) throws ClassNotFoundException, SQLException {
		Connection connection=DBUtils.createConnection();
		try
		{
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.INSERTTASK);
			stmt.setString(1,vo.getTaskDetail() );
			stmt.setInt(2,vo.getGroupId());
			stmt.setInt(3, vo.getEmployeeId());
			stmt.setString(4, vo.getETA());
			stmt.executeUpdate();
		}
		finally
		{
			connection.close();
		}
	}

	public ArrayList<AssignTaskVO> getTaskByGroupEmployee(AssignTaskVO vo) throws ClassNotFoundException, SQLException
	{
		//System.out.println("dao");
		ArrayList<AssignTaskVO> taskDetail=new ArrayList<AssignTaskVO>();
		Connection connection=DBUtils.createConnection();
		try{
			PreparedStatement stmt= connection.prepareStatement(SQLQueries.GETTASKBYGROUPEMPLOYEE);
			stmt.setInt(1, vo.getGroupId());
			stmt.setInt(2, vo.getEmployeeId());
			ResultSet result=stmt.executeQuery();
			while(result.next())
			{
				AssignTaskVO temp=new AssignTaskVO();
				temp.setTaskDetail(result.getString(2));
				temp.setTaskId(result.getInt(1));
				taskDetail.add(temp);
				
			}
		}
		finally
		{
			connection.close();
		}
		
		return taskDetail;
	}
	public void insertTaskUpdate(TaskUpdateVO vo) throws ClassNotFoundException, SQLException {
		Connection connection=DBUtils.createConnection();
		try
		{
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.INSERTTASKUPDATE);
			stmt.setString(1,vo.getTaskUpdates());
			Calendar current=Calendar.getInstance();
			SimpleDateFormat dat=new SimpleDateFormat("dd/MM/yyyy");
			String currentDate=dat.format(current.getTime());
			stmt.setString(2, currentDate);
			stmt.setInt(3, vo.getTaskId());
			stmt.setInt(4,vo.getGroupId());
			stmt.setInt(5, vo.getEmployeeId());
			stmt.executeUpdate();
		}
		finally
		{
			connection.close();
		}
	}
	
}
