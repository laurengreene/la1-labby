package music;

public enum Rating {
		UNRATED(0),
		ONE(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5);
		 
		private final int value;
		
		private Rating(int value) {
			this.value = value;
		}
		 
		 public int getValue() {
			 return value;
		 }
		 
		public static Rating convert(int num) {
			switch (num) {
			case 1:
				return ONE;
			case 2:
				return TWO;
			case 3:
				return THREE;
			case 4:
				return FOUR;
			case 5:
				return FIVE;
			default:
				return UNRATED;
			}
		}

}