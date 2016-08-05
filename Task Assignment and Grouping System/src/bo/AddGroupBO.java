package bo;

import property.PropertyUtil;
import exception.TagsBusinessException;
import vo.AddGroupNameVO;

public class AddGroupBO {

	public boolean checkGroupName(AddGroupNameVO checkGroup) throws TagsBusinessException {
		final String groupName=checkGroup.getGroupName();
		final boolean validateFlag=groupName.matches("[a-zA-Z]+");
		if(!validateFlag)
		{
			throw new TagsBusinessException(PropertyUtil.getMessage("201"));
		}
		return validateFlag;
	}

	

}
