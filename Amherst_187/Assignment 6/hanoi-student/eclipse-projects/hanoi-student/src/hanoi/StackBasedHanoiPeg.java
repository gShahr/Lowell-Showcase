package hanoi;

import structures.ListImplementation;

/**
 * A {@link StackBasedHanoiPeg} is an implementation of {@link HanoiPeg}.
 * 
 * @author jcollard
 */
public class StackBasedHanoiPeg implements HanoiPeg {
	
	ListImplementation<HanoiRing> list;
	
	/**
	 * Creates a new {@link StackBasedHanoiPeg} that has no rings.
	 */
	public StackBasedHanoiPeg() {
		list = new ListImplementation<HanoiRing>();
	}

	@Override
	public void addRing(HanoiRing ring) throws IllegalHanoiMoveException {
		if (list.size() == 0) {
			list.append(ring);
		} else if (getTopRing().getSize() <= ring.getSize()) {
			throw new IllegalHanoiMoveException("Illegal");
		} else {
			list.append(ring);
		}
	}

	@Override
	public HanoiRing remove() throws IllegalHanoiMoveException {
		if (list.size() == 0) {
			throw new IllegalHanoiMoveException("Illegal");
		} else {
			return list.pop();
		}
	}

	@Override
	public HanoiRing getTopRing() throws IllegalHanoiMoveException {
		if (list.size() == 0) {
			throw new IllegalHanoiMoveException("Illegal");
		} else {
			return list.get(list.size()-1);
		}
	}

	@Override
	public boolean hasRings() {
		return !list.isEmpty();
	}
}
