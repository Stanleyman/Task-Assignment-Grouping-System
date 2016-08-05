package bo;


import java.util.Calendar;
import property.PropertyUtil;
import exception.TagsBusinessException;
import vo.AssignTaskVO;
import vo.TaskUpdateVO;

public class AssignTaskBO {

	public boolean checkTaskDetail(AssignTaskVO vo) throws TagsBusinessException {
		String taskDetail=vo.getTaskDetail();
		boolean flag=taskDetail.matches("[a-zA-Z]+[a-zA-Z ]*");
		if(!flag)
		{
			throw new TagsBusinessException(PropertyUtil.getMessage("301"));
		}
		//System.out.println("task bo");
		return flag;
	}

	 public boolean checkETA(AssignTaskVO vo) throws TagsBusinessException {

			boolean validateFlag = true;

			if ((vo.getETA().matches("[0-9]+/[0-9]+/[0-9]+"))) {
				String dateLimit[] = vo.getETA().split("/");
				int date = Integer.parseInt(dateLimit[0]);
				int mon = Integer.parseInt(dateLimit[1]);
				int year = Integer.parseInt(dateLimit[2]);
				Calendar given = Calendar.getInstance();
				given.set(year, mon, date);
				Calendar cal = Calendar.getInstance();
				if (date >= 1
						&& date <= given.getActualMaximum(Calendar.DAY_OF_MONTH)
						&& mon >= 1 && mon <= 12 && year > 1920
						&& year <= cal.get(Calendar.YEAR)) {
					if (year == cal.get(Calendar.YEAR)) {

						if (mon == (cal.get(Calendar.MONTH) + 1)) {

							if ((date < cal.get(Calendar.DAY_OF_MONTH))) {
								validateFlag = false;

							}
						} else if (mon < (cal.get(Calendar.MONTH) + 1)) {
							validateFlag = false;

						}
					}
					if (year < cal.get(Calendar.YEAR)) {
						validateFlag = false;
					}
					if (!validateFlag) {
						throw new TagsBusinessException("Invalid Date in ETA");
					}
				}}
			else {
					throw new TagsBusinessException("Invalid Format in ETA");
				}

			
			return validateFlag;
		}
	
	public boolean checkTaskUpdate(TaskUpdateVO vo) throws TagsBusinessException {
		String taskUpdate=vo.getTaskUpdates();
		boolean flag=taskUpdate.matches("[a-zA-Z]+[a-zA-Z ]*");
		if(!flag)
		{
			throw new TagsBusinessException(PropertyUtil.getMessage("303"));
		}
		return flag;
	}
}
