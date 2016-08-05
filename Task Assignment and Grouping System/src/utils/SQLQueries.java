package utils;

public class SQLQueries {

	public static final  String  INSERTEMPLOYEE="insert into Employee_Master values(?,?,?,?)";
	public static final String INSERTGROUP="insert into Group_Master values(DEFAULT,?,?)";
	public static final String GETGROUPNAMES="select Group_Id,Group_Name from Group_Master";
	public static final String GETEMPLOYEENAMESINGROUP="select Employee_Id,Name from Employee_Master where Employee_Id in (select Employee_Id from Employee_Group where Group_Id =?)";
	public static final String ISFIRSTEMPLOYEE="select count(Employee_Id) from Employee_Group where Group_Id=?";
	public static final String GETEMPLOYEENAMENOTINGROUP="select Employee_Id,Name from Employee_Master where Employee_Id not in (select Employee_Id from Employee_Group where Group_Id = ?)";
	public static final String INSERTEMPLOYEETOGROUP="insert into Employee_Group values(?,?,?)";
	public static final String INSERTTASK="insert into Task_Master values(DEFAULT,?,?,?,?)";
	public static final String GETTASKBYGROUPEMPLOYEE="select Task_Id,Task_Details from Task_Master where Group_Id =? and Assigned_To=?";
	public static final String INSERTTASKUPDATE="insert into Task_Updates values(DEFAULT,?,?,?,?,?)";
	public static final String GETGRADES="select Grade_Id,Grade_name from grade";
	public static final String GETTASKBYGROUP="select Task_Id,Task_Details from Task_Master where Task_Id in (select Task_Id from Task_Updates where Group_Id=?)";
	public static final String GETREPORTBYALL="select Task_Update_Id,Task_Update,Created_Date from Task_Updates where Task_Id=? and Assigned_To=? and Group_Id=?";
	public static final String  GETREPORTBYGROUPEMP="select Task_Update_Id,Task_Update,Created_Date,Task_Id from Task_Updates where  Assigned_To=? and Group_Id=?";
	public static final String GETREPORTBYGROUPTASK="select Task_Update_Id,Task_Update,Created_Date,Assigned_To from Task_Updates where  Task_Id=? and Group_Id=?";
	public static  final String GETTASKNAME="select Task_Details from Task_Master where Task_Id=?";
	public static final String GETEMPLOYEE="select Name from Employee_Master where Employee_Id=?";
	public static final String GETGROUP="select Group_Name from Group_Master where Group_Id=?";
	public static final String GETTASKLIST="select Task_Details from Task_Master where Group_Id=?";
	public static final String GETEMPLOYEELIST="select Name from Employee_Master where Employee_Id in(select Employee_Id from Employee_Group where Group_Id =?)";
	public static final String GETREPORTBYGROUP="select Task_Update_Id,Task_Update,Created_Date,Assigned_To from Task_Updates where Group_Id=?";

}


