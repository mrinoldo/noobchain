import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes
        for (int i=1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            //compare registered hash and calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hashes are not equal");
                return false;
            }

            //compare previous hash and register previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous hashes are not equal");
                return false;
            }

            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {

        blockchain.add(new Block("Hey first block", "0"));
        System.out.println("Trying to Mine block 1...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Hey Ill be second okayy", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 2...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("3rd in line", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 3...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);

//        Block genesisBlock = new Block("Firsttttttt", "0");
//        System.out.println("Hash for block 1: " + genesisBlock.hash);
//
//        Block secondBlock = new Block("Hey i'm the second block", genesisBlock.hash);
//        System.out.println("Hash for block 2: " + secondBlock.hash);
//
//        Block thirdBlock = new Block("I guess I'm third in line", secondBlock.hash);
//        System.out.println("Hash for block 3: " + thirdBlock.hash);

    }
}
