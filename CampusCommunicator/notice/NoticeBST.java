package CampusCommunicator.notice;

import CampusCommunicator.models.Notice;

public class NoticeBST {
    private static NoticeBST instance; // Singleton instance
    private BSTNode root;

    private NoticeBST() {
        root = null;
    }

    public static NoticeBST getInstance() {
        if (instance == null) {
            instance = new NoticeBST();
        }
        return instance;
    }

    private class BSTNode {
        Notice notice;
        BSTNode left, right;

        public BSTNode(Notice notice) {
            this.notice = notice;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(Notice notice) {
        root = insertRec(root, notice);
    }

    private BSTNode insertRec(BSTNode node, Notice notice) {
        if (node == null) return new BSTNode(notice);
        if (notice.getTitle().compareToIgnoreCase(node.notice.getTitle()) < 0)
            node.left = insertRec(node.left, notice);
        else
            node.right = insertRec(node.right, notice);
        return node;
    }

    public Notice searchByTitle(String title) {
        return searchRec(root, title);
    }

    private Notice searchRec(BSTNode node, String title) {
        if (node == null) return null;
        if (node.notice.getTitle().equalsIgnoreCase(title)) return node.notice;
        if (title.compareToIgnoreCase(node.notice.getTitle()) < 0)
            return searchRec(node.left, title);
        else
            return searchRec(node.right, title);
    }

    public void displayInOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(BSTNode node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.notice);
            inOrderRec(node.right);
        }
    }

    public boolean delete(String title) {
        if (searchByTitle(title) == null) return false;
        root = deleteRec(root, title);
        return true;
    }

    private BSTNode deleteRec(BSTNode root, String title) {
        if (root == null) return null;

        if (title.compareToIgnoreCase(root.notice.getTitle()) < 0)
            root.left = deleteRec(root.left, title);
        else if (title.compareToIgnoreCase(root.notice.getTitle()) > 0)
            root.right = deleteRec(root.right, title);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.notice = minValue(root.right);
            root.right = deleteRec(root.right, root.notice.getTitle());
        }
        return root;
    }

    private Notice minValue(BSTNode root) {
        Notice min = root.notice;
        while (root.left != null) {
            root = root.left;
            min = root.notice;
        }
        return min;
    }
}
