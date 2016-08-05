package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import utils.DBUtils;
import utils.SQLQueries;
import vo.AddGroupNameVO;
import vo.AssignOwnerVO;

public class GroupDAO {

	public void insertGroupName(AddGroupNameVO addGroupNameVo) throws ClassNotFoundException, SQLException {
		
			Connection connection=DBUtils.createConnection();
			try
			{
		PreparedStatement stmt=connection.prepareStatement(SQLQueries.INSERTGROUP);
		stmt.setString(1, addGroupNameVo.getGroupName());
		Calendar current=Calendar.getInstance();
		SimpleDateFormat dat=new SimpleDateFormat("dd/MM/yyyy");
		String currentDate=dat.format(current.getTime());
		stmt.setString(2, currentDate);
		stmt.executeUpdate();
		}finally
		{
		connection.close();
		}
	}
	public ArrayList<AssignOwnerVO> getGroupNames() throws ClassNotFoundException, SQLException
	{
		
		Connection connection=DBUtils.createConnection();
		ArrayList<AssignOwnerVO> groupNames=new ArrayList<AssignOwnerVO>(); 
		try{
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.GETGROUPNAMES);
			ResultSet result=stmt.executeQuery();
			while(result.next())
			{
				AssignOwnerVO localAssignOwnerVo=new AssignOwnerVO();
				localAssignOwnerVo.setGroupId(result.getInt(1));
				localAssignOwnerVo.setGroupName(result.getString(2));
				groupNames.add(localAssignOwnerVo);
				
			}
			
		}
		finally
		{
			connection.close();
		}
		return groupNames;
	}
	public void addEmployeeToGroup(AssignOwnerVO assignOwnerVo) throws ClassNotFoundException, SQLException {
		
		Connection connection=DBUtils.createConnection();
		try
		{
	PreparedStatement stmt=connection.prepareStatement(SQLQueries.INSERTEMPLOYEETOGROUP);
	stmt.setInt(1,assignOwnerVo.getGroupId());
	stmt.setInt(2, assignOwnerVo.getEmployeeId());

	if(assignOwnerVo.isOwner())
	{
	stmt.setString(3, "y");		
	}
	else
	{
		stmt.setString(3, "n");
	}
	
	stmt.executeUpdate();
	
	}
		finally
		{
			connection.close();
		}
	
	}
	
}
