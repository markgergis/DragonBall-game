package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class MaximumAttacksLearnedException extends
		InvalidAssignAttackException {

	public MaximumAttacksLearnedException() {
		super("No more attacks could be learned by this fighter.");
		// TODO Auto-generated constructor stub
	}

}
