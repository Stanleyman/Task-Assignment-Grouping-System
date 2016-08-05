package bo;

import java.util.Calendar;

import property.PropertyUtil;

import exception.TagsBusinessException;

import vo.EmployeeVO;

public class AddEmployeeBO {

	public boolean checkEmployeeId(final EmployeeVO vo) throws TagsBusinessException {
		final String Employee_Id=vo.getEmployeeId();
		final boolean validateFlag=Employee_Id.matches("[0-9]{6}");
		if(validateFlag==false)
		{
			throw new TagsBusinessException(PropertyUtil.getMessage("101"));
		}
		return validateFlag;
	}

	public boolean checkEmployeeName(final EmployeeVO vo) throws TagsBusinessException {
		final String Employee_Name=vo.getEmployeeName();
		final boolean validateFlag=Employee_Name.matches("[a-zA-Z ]+");
		if(validateFlag==false)
		{
			throw new TagsBusinessException(PropertyUtil.getMessage("102"));
		}
		return validateFlag;
	}


	public boolean checkDOJ(EmployeeVO vo) throws TagsBusinessException {
		
		
		boolean validateFlag=true;
		
			if((vo.getDOJ().matches("[0-9]+/[0-9]+/[0-9]+")))
			{
				final String DOJ[]=vo.getDOJ().split("/");
				final int date=Integer.parseInt(DOJ[0]);
				final int mon=Integer.parseInt(DOJ[1]);
				final int year=Integer.parseInt(DOJ[2]);
				final Calendar given = Calendar.getInstance();
				given.set(year, mon, date);
				final Calendar cal = Calendar.getInstance();
				if (date >= 1
						&& date <= given
								.getActualMaximum(Calendar.DAY_OF_MONTH)
						&& mon >= 1 && mon <= 12 && year > 1920
						&& year <= cal.get(Calendar.YEAR)) {
					if (year == cal.get(Calendar.YEAR)) {
					
						if (mon == (cal.get(Calendar.MONTH) + 1)) {
							
							if ((date > cal.get(Calendar.DAY_OF_MONTH))) {
								validateFlag = false;
								
							}
						} else if (mon > (cal.get(Calendar.MONTH) + 1)) {
							validateFlag = false;
							
						}
					}
				}
				else
				{
					validateFlag=false;
				}
				if(!validateFlag)
				{
					throw new TagsBusinessException(PropertyUtil.getMessage("103"));
				}
			}
			else
			{
				throw new TagsBusinessException(PropertyUtil.getMessage("104"));
			}
			
		return validateFlag;
	}
	
}
