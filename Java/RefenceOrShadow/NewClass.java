class NewClass {
	int value = 0;
	private static NewClass mNewClassSingleton = null;

	public static synchronized NewClass getInstance() {
		if(mNewClassSingleton == null) {
			mNewClassSingleton = new NewClass();
		}
		return mNewClassSingleton;
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();    // return shallow copy
    }
}