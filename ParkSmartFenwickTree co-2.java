class ParkSmartFenwickTree {

    int[] bit;
    int n;

    ParkSmartFenwickTree(int n) {
        this.n = n;
        this.bit = new int[n + 1];
    }

    // Return revenue[1] + revenue[2] + ... + revenue[i]
    long prefixSum(int i) {
        long sum = 0;

        while (i > 0) {
            sum += bit[i];
            i -= (i & -i); // FIXED
        }

        return sum;
    }

    // Add delta to revenue at index i
    void pointUpdate(int i, int delta) {
        while (i <= n) {
            bit[i] += delta;
            i += (i & -i);
        }
    }

    public static void main(String[] args) {

        ParkSmartFenwickTree fen = new ParkSmartFenwickTree(15);

        // Daily Parking Revenue (Jan 1 - Jan 15)
        int[] revenue = {
            0,
            1200, 800, 0, 2400, 1500,
            600, 0, 0, 3500, 0,
            1100, 950, 700, 0, 0
        };

        // Build BIT
        for (int i = 1; i <= 15; i++) {
            fen.pointUpdate(i, revenue[i]);
        }

        long p12 = fen.prefixSum(12);
        long p4 = fen.prefixSum(4);

        System.out.println("======================================");
        System.out.println(" ParkSmart Revenue Analysis");
        System.out.println("======================================");

        System.out.println("Prefix Revenue (Jan 1 - Jan 12) = Rs." + p12);
        System.out.println("Prefix Revenue (Jan 1 - Jan 4)  = Rs." + p4);

        System.out.println(
            "Parking Revenue Jan 5 - Jan 12 = Rs."
            + (p12 - p4)
        );
    }
}