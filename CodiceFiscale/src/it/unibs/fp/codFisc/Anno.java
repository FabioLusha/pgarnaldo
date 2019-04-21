package it.unibs.fp.codFisc;

 public enum Anno {
	A(31),
	B(28),
	C(31),
	D(30),
	E(31),
	H(30),
	L(31),
	M(31),
	P(30),
	R(31),
	S(30),
	T(31);
	
	private int durata;
	Anno(int _durata) {
		this.durata = _durata;
	}
	
	public static void controlloSeBisesto(int _anno) {
		if( _anno % 4 == 0)
			Anno.B.durata = 29;
	}

	public int getDurata() {
		return durata;
	}

	
	
}

