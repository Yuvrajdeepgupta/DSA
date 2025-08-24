// VIDEO LINK - https://youtu.be/D320QeHS0XQ?si=a0QXnqnyeeWa3aUC

/*
🧠 TOPIC: Binary Exponentiation (a^b)

You're given two numbers:
- Base: `a`
- Exponent: `b`

You need to compute:
    a^b  (i.e., "a raised to the power b")

----------------------------------------------------
✅ INTUITION:

The brute force way would multiply `a` by itself `b` times:
    ➜ Time: O(b) ❌ (too slow for large b)

🧠 Binary Exponentiation speeds it up using this logic:

- Even power:
    a^b = (a^(b/2))^2

- Odd power:
    a^b = a * (a^(b/2))^2

This reduces `b` by half every time ➜ O(log b)

✅ Works for very large exponents efficiently

----------------------------------------------------
📦 APPROACH USED:

1. Iterative Binary Exponentiation ✅
2. Recursive Binary Exponentiation ✅

----------------------------------------------------
✅ CODE: ITERATIVE BINARY EXPONENTIATION
*/

class BinaryExponentiation {
    public static long powerIterative(long a, long b) {
        long result = 1;

        while (b > 0) 
        {
            if (b % 2 == 1)  // If current bit is 1
            {
                result *= a;
            }

            a *= a;   // square the base
            b /= 2;   // shift exponent to right
        }

        return result;
    }

    /*
    ✅ CODE: RECURSIVE BINARY EXPONENTIATION
    */
    public static long powerRecursive(long a, long b) {
        // Base case
        if (b == 0) 
        {
            return 1;
        }

        // Recursively compute a^(b/2)
        long half = powerRecursive(a, b / 2);

        if (b % 2 == 0) 
        {
            return half * half;
        } 
        else 
        {
            return a * half * half;
        }
    }

    public static void main(String[] args) {
        System.out.println("Iterative: " + powerIterative(2, 10));   // Output: 1024
        System.out.println("Recursive: " + powerRecursive(2, 10));   // Output: 1024
        System.out.println("Iterative: " + powerIterative(5, 3));    // Output: 125
        System.out.println("Recursive: " + powerRecursive(5, 3));    // Output: 125
    }
}

/*
----------------------------------------------------
📈 TIME COMPLEXITY:

- Each step halves the exponent `b`
✅ Time: O(log b) for both Iterative and Recursive

----------------------------------------------------
🧠 SPACE COMPLEXITY:

- Iterative: O(1)
- Recursive: O(log b) (due to recursive call stack)

----------------------------------------------------
🔥 BONUS: Modular Binary Exponentiation (for a^b % m)

Used in problems where result can overflow or we want modulo (like in CP, RSA, etc.)

Use this formula:
- If b is even: (a^b) % m = ((a^(b/2) % m)^2) % m
- If b is odd:  (a^b) % m = (a * ((a^(b/2) % m)^2)) % m

----------------------------------------------------
✅ MODULAR EXPONENTIATION CODE (Iterative)
*/

class ModularBinaryExponentiation {
    public static long modPower(long a, long b, long mod) {
        long result = 1;
        a = a % mod;

        while (b > 0) 
        {
            if (b % 2 == 1)
            {
                result = (result * a) % mod;
            }

            a = (a * a) % mod;
            b /= 2;
        }

        return result;
    }
}

/*
----------------------------------------------------
📈 TIME & SPACE COMPLEXITY (Same as above):

✅ Time: O(log b)
✅ Space: O(1)

----------------------------------------------------
🧾 SUMMARY (Binary Exponentiation):

1. Speeds up power calculation using squaring
2. Works in log(b) time
3. Can be extended to modular version
4. Critical in competitive coding, cryptography, large integer math

----------------------------------------------------
🧪 EXAMPLES:

Input: a = 2, b = 10
Output: 1024

Input: a = 3, b = 5
Output: 243

Input: a = 2, b = 10, mod = 1_000_000_007
Output: 1024

*/
