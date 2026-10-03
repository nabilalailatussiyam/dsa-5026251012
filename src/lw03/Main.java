package lw03;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    // PROBLEM 1
        List<String> playlist = new ArrayList<>();    //struktur data menyimpan list lagu, pakai string karena isi nama lagunya. New array itu membuat object arraylist kosong.

        try (Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("playlist.txt"))) {

                while (scanner.hasNextLine()) {       //baca file baris demi baris, misal kaya ADD Ditto. Bacanya satu baris utuh. 
                     
                    String line = scanner.nextLine();        //ambil barisnya, misal ADD Ditto. Lalu di split pake spasi, jadi ada dua bagian: ADD sama Ditto.
                    String[] parts = line.split(" ");   //mecah line berdasarkan spasi.

                    if (parts[0].equals("ADD")) {    //khusus perintah ADD

                        String song = line.substring(4);  //ambil nama lagu, ADD itu indexnya 0-3 (include spasi), jadi judul lagu dibaca dari index ke 4
                        playlist.add(song);

                    } else if (parts[0].equals("INSERT")) {  //sm aja tapi ini khusus yang perintah insert.

                        int index = Integer.parseInt(parts[1]);      //ada angka kan, jadi butuh index int.
                        String song = line.substring(9);
                        playlist.add(index, song);                   //masukin lagu ke index tertentu.

                    } else if (parts[0].equals("REMOVE")) {   //iyh ini juga

                        String song = line.substring(7);
                        playlist.remove(song);                          //ini tuh ngehapus kemunculan pertama lagu tersebut dr list(?)
                    }
                }
            }

            System.out.println("===== Problem 1 =====");
            System.out.println("Total songs: " + playlist.size());

            for (int i = 0; i < playlist.size(); i++) {                 //ambil lagu mulai dari index i
                System.out.println((i + 1) + ": " + playlist.get(i));
            }

            //System.out.println("NEWJEANS NEVER DIE!");  //kgn newjeans,,,
            System.out.println();

    // PROBLEM 2

        Set<String> participants = new LinkedHashSet<>();     //pakai set kaaren peserta yang sama gaboleh disimpan/muncul(?) 2 kali. sama soal minta dia muncul berdasarkan urutan pertama.
        int duplicate = 0;                                    //biar gaada yg double, hitung pendaftar yang namanya sudah oernah terdaftar.

        try (Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("Participants.txt"))) {
                    
                while (scanner.hasNextLine()) {
                    String name = scanner.nextLine();

                        if (participants.contains(name)){           //ini code yang buat ngecheck apakah ada nama yg udah ada di list peserta. Biar ga duplikat.
                            duplicate++;
                        } else {
                            participants.add(name);
                        }
                    }
                }

            System.out.println("===== Problem 2 =====");
            System.out.println("Unique participants: " + participants.size());

            int number = 1;
            for (String name : participants) {
                System.out.println(number + ". " + name);
                number++;
            }

            System.out.println("Duplicate registration: " + duplicate);
            System.out.println();

    // PROBLEM 3
        
        Map<String, Integer> inventory = new LinkedHashMap<>();    //nyimpen nama produk > jumlah stok. Pake linked krn soal minta urutan prodyuk berdasarkan kemunculan pertama.
        int failedSales = 0;       //kalau transaksi gagal.

        try (Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("inventory.txt"))) {

                while (scanner.hasNextLine()) {

                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");   //ini tu karena di file, per baris dipisahkan oleh spasi.

                    String type = parts[0];
                    String product = parts[1];
                    int quantity = Integer.parseInt(parts[2]);

                    if (type.equals("ADD")) {

                        if (inventory.containsKey(product)) {     //check apakah produk sudah ada di map atau belum. Kalau udah ada, jumlah stoknya ditambah. Kalau belum ada, baru dimasukin ke map.
                            inventory.put(product, inventory.get(product) + quantity);  //produk yg udah ada masuk kesini nanti.
                        } else {
                            inventory.put(product, quantity);      //nambah produk baru ke map.
                        }
                    } else if (type.equals("SELL")) {     //ini kalau transaksinya penjualan.

                        if (inventory.containsKey(product) && inventory.get(product) >= quantity) {   //artinya produk harus ada, serta stok yang tersedia harus cukup untuk dijual.
                            inventory.put(product, inventory.get(product) - quantity);     //kalau penjualan berhasil, stok produk dikurangi sesuai jumlah yang dijual.
                        } else {            //kalau penjualam gagal, misal produk gaada atau stok ga cukup.
                            failedSales++;  //tambah jumlah transaksi yang gagal.
                        }
                    }
                }
            }

            System.out.println("===== Problem 3 =====");

            for(String product : inventory.keySet()) {    //inventory key set itu ambil semua nama produk yang ada di map.
                System.out.println(product + ": " + inventory.get(product));
            }

            System.out.println("Failed sales: " + failedSales);
    }
}