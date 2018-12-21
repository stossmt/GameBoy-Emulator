package cpu;

/**  
 * This class represents the CPU's registers and their associated utilities. The CPU 
 * contains 10 registers. 2 special registers (stack pointer (SP) and program counter (PC)), 
 * and 8 general purpose registers. The general purpose registers can be accessed individually, 
 * or as a set of two paired registers (AF, BC, DE, HL). Finally, the F register doubles as a 
 * flag register, whose bits are set after certain operations.
 *
 * The 8 general purpose registers are stored as an array of bytes. The SP and PC are stored as
 * individual shorts, since they are a special case. Getters and setters exist for both
 * individual and paired register access. Flag register utilities are also defined in this class.
 * The four flags are carry, half-carry, subtract, and zero.
 */

public class RegisterFile {
	/**
	 * Enumerations for the flag register bits, as well as the register indices in the reg array.
	 * Java's built-in enum is verbose. Defining these enumerations as constants is more direct.
	 */
	public static final int CARRY = 4, H_CARRY = 5, SUBTRACT = 6, ZERO = 7;
	public static final int A = 0, F = 1, B = 2, C = 3, D = 4, E = 5, H = 6, L = 7;

	private byte[] reg = new byte[8];
	public short SP, PC;

	/**
	 * Sets a two byte register pair (AF, BC, DE, HL). The first register is the upper byte,
	 * and the second register is the lower byte. For example, in the AF pair, A is the upper byte
	 * and F is the lower byte.
	 *
	 * @param the index of the register to be set in the reg array. 
	 *		Use the defined constants (A, B, D, or H)
	 * @param the value to assign to the register pair
	 */
	void setRegisterPair(int regIdx, short val) {
	   reg[regIdx] = (byte)(val >> 8); // upper byte
	   reg[regIdx + 1] = (byte)(val); // lower byte
	}

	/**
	 * Returns a two byte value from the specified register pair (AF, BC, DE, HL).
	 *
	 * @param the index of the register in the reg array. 
	 *		Use the defined constants (A, B, D, or H)
	 * @return a short that was stored in the specified register pair
	 */
	short getRegisterPair(int regIdx) {
		return (short)((reg[regIdx] & 0xFF) << 8 | reg[regIdx + 1] & 0xFF);
	}

	/**
	 * Sets an individual one byte register (A, F, B, C, D, E, H, L).
	 *
	 * @param the index of the register to be set in the reg array. 
	 *		Use the defined constants (A, F, B, C, D, E, H, or L)
	 * @param the value to assign to the register
	 */
	void setRegister(int regIdx, byte val) {
		reg[regIdx] = val;
	}

	/**
	 * Returns a one byte value from the specified register (A, F, B, C, D, E, H, L)
	 *
	 * @param the index of the register in the reg array. 
	 *		Use the defined constants (A, F, B, C, D, E, H, or L)
	 * @return a byte that was stored in the specified register
	 */
	byte getRegister(int regIdx) {
		return reg[regIdx];
	}

     /**
      * Set all F register flags to false
      */
	void resetFlags() {
		reg[F] = 0;
	}

	/**
	 * Sets the F register flag to true at the specified bit
	 *
	 * @param the bit of the F register that will be set to true
	 *		Use the defined constants (CARRY, H_CARRY, SUBTRACT, ZERO)
	 */
	void setFlag(int bit) {
		reg[F] = (byte)(reg[F] | (0x1 << bit));
	}
}