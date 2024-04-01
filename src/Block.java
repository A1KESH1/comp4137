import java.security.*;
import java.util.*;

public class Block {
    private String hash;
    private String previousHash;
    private String merkleRoot;
    private long timeStamp;
    private int nonce;

    public Block(MerkleTree merkleTree, String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calcHash();
        this.merkleRoot = merkleTree.getRoot().getValue();
    }

    public String calcHash(){
        //Hashing function
        MessageDigest digest = null;
        String content = previousHash + timeStamp + nonce + merkleRoot;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //Encode data to String
        byte[] hash = digest.digest(content.getBytes());
        String encodeDdata =Base64.getEncoder().encodeToString(hash);
        return encodeDdata;
    }
}
