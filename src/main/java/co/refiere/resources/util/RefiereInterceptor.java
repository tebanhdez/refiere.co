package co.refiere.resources.util;

import java.io.Serializable;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import co.refiere.models.RefiereCompany;

public class RefiereInterceptor extends EmptyInterceptor {

	/**
	 * onSave – Called when you save an object, the object is not save into database yet.
	 * onFlushDirty – Called when you update an object, the object is not update into database yet.
	 * onDelete – Called when you delete an object, the object is not delete into database yet.
	 * preFlush – Called before the saved, updated or deleted objects are committed to database (usually before postFlush).
	 * postFlush – Called after the saved, updated or deleted objects are committed to database.
	 */
	private static final long serialVersionUID = 1L;

	public RefiereInterceptor() {
	}

	public boolean onSave(Object entity,Serializable id,
			Object[] state,String[] propertyNames,Type[] types)
			throws CallbackException {
			
			if (entity instanceof RefiereCompany){
				System.out.println("  >>> Saving company!!!");
			}
			return false;
				
		}
}
