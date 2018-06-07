/**
 * 
 */
package io.sinistral.proteus.tests.models;

import io.sinistral.proteus.models.User;

/**
 * @author jbauer
 *
 */
public class TestUser
{
 
	public enum UserType
	{
		GUEST,MEMBER,ADMIN
	}
	
	private Long id = 0L;
	 
	
	private UserType type = UserType.GUEST;

	public TestUser()
	{
		
	}
	
	public TestUser(Long id)
	{
		this.id = id;
	}
	
	public TestUser(Long id, UserType type)
	{
		this.id = id;
		this.type = type;
	}


	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

//	/**
//	 * @return the type
//	 */
//	public UserType getType()
//	{
//		return type;
//	}

	/**
	 * @param type the type to set
	 */
	public void setType(UserType type)
	{
		this.type = type;
	}

 
	/**
	 * @return the type
	 */
	public UserType getType()
	{
		return type;
	}
	
	public static TestUser generateUser()
	{
		return new TestUser((long)(Math.random()*1000)+1L, UserType.ADMIN);
	}
	
}
