package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import utils.DBUtils;
import utils.SQLQueries;
import vo.AssignOwnerVO;
import vo.EmployeeVO;
import vo.GradeVO;

public class EmployeeDAO {

	public void insertEmployeeDetails(EmployeeVO insertEmployeeVo) throws SQLException, ClassNotFoundException {
		
		Connection connection=DBUtils.createConnection();
		try
		{
		PreparedStatement stmt=connection.prepareStatement(SQLQueries.INSERTEMPLOYEE);
		stmt.setInt(1, Integer.parseInt(insertEmployeeVo.getEmployeeId()));
		stmt.setString(2, insertEmployeeVo.getEmployeeName());
		stmt.setString(3,insertEmployeeVo.getDOJ());
		stmt.setInt(4, Integer.valueOf(insertEmployeeVo.getGrade()));
		stmt.executeUpdate();
		}
		finally
		{
			connection.close();
		}
	}
	
	public ArrayList<AssignOwnerVO> getEmployeeNamesInGivenGroup(AssignOwnerVO fetchEmployeesVo) throws ClassNotFoundException, SQLException
	{

		ArrayList<AssignOwnerVO> employeeNames=new ArrayList<AssignOwnerVO>();
		Connection connection=DBUtils.createConnection();
		try
		{
			
			
				PreparedStatement stmt= connection.prepareStatement(SQLQueries.GETEMPLOYEENAMESINGROUP);
				stmt.setInt(1, fetchEmployeesVo.getGroupId());
				ResultSet result=stmt.executeQuery();
				while(result.next())
				{
					AssignOwnerVO assignOwnerVo=new AssignOwnerVO();
					assignOwnerVo.setEmployeeId(result.getInt(1));
					assignOwnerVo.setEmployeeName(result.getString(2));
	
					employeeNames.add(assignOwnerVo);
				}
			}
		
		
		finally
		{
			connection.close();
		}
		return employeeNames;
	}
	public ArrayList<AssignOwnerVO> getEmployeeNames(String fetchNamesVo) throws ClassNotFoundException, SQLException, JSONException
	{

		ArrayList<AssignOwnerVO> employeeNames=new ArrayList<AssignOwnerVO>();
		Connection connection=DBUtils.createConnection();
		try
		{
			
			
				
				PreparedStatement stmt= connection.prepareStatement(SQLQueries.GETEMPLOYEENAMENOTINGROUP);
				stmt.setInt(1, Integer.valueOf((fetchNamesVo)));
				ResultSet result=stmt.executeQuery();
				while(result.next())
				{
					AssignOwnerVO localAssignOwnerVo=new AssignOwnerVO();
					localAssignOwnerVo.setEmployeeId(result.getInt(1));
					localAssignOwnerVo.setEmployeeName(result.getString(2));
					employeeNames.add(localAssignOwnerVo);
					
				}
			
		
			
	    
		
		}
		finally
		{
			connection.close();
		}
		/*JSONObject json=new JSONObject();
		json.accumulate("qqq", employeeNames);*/
		return employeeNames;
	}
	public int checkFirstEmployeeDAO(AssignOwnerVO assignOwnerVo) throws ClassNotFoundException, SQLException 
	{
		int validateFlag=0;
		Connection connection=DBUtils.createConnection();
		try{
			PreparedStatement stmt=connection.prepareStatement(SQLQueries.ISFIRSTEMPLOYEE);
			stmt.setInt(1,assignOwnerVo.getGroupId());
			ResultSet res=stmt.executeQuery();
			while(res.next())
			{
				validateFlag=res.getInt(1);
			}
		}
		finally{
			connection.close();
			
		}
	
		return validateFlag;
	}
	public ArrayList<GradeVO> getGrades() throws ClassNotFoundException, SQLException
	{

		ArrayList<GradeVO> gradeNames=new ArrayList<GradeVO>();
		Connection connection=DBUtils.createConnection();
		try
		{
			
				PreparedStatement stmt= connection.prepareStatement(SQLQueries.GETGRADES);
				ResultSet result=stmt.executeQuery();
				while(result.next())
				{
					GradeVO localGradeVo=new GradeVO();
					localGradeVo.setGradeId((result.getInt(1)));
					localGradeVo.setGradeName(result.getString(2));
					gradeNames.add(localGradeVo);
				}
			
	    
			
	    
		
		}
		finally
		{
			connection.close();
		}
		return gradeNames;
	}
}
