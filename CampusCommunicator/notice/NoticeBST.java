// NoticeBST.java
package CampusCommunicator.notice;

import CampusCommunicator.models.Notice;

public class NoticeBST {
    private BSTNode root;

    // Inner class for a node in the BST
    private class BSTNode {
        Notice notice;
        BSTNode left, right;

        public BSTNode(Notice notice) {
            this.notice = notice;
            this.left = null;
            this.right = null;
        }
    }

    // Method to insert a notice into the BST
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

    // Method to search for a notice by title
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

    // Method to display notices in-order (sorted by title)
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

    // Method to delete a notice by title
    public boolean delete(String title) {
        if (deleteRec(root, title) != null) {
            return true;
        }
        return false;
    }

    private BSTNode deleteRec(BSTNode root, String title) {
        if (root == null) return null;

        // If the title to be deleted is smaller than the root's title, it lies in left subtree
        if (title.compareToIgnoreCase(root.notice.getTitle()) < 0)
            root.left = deleteRec(root.left, title);

        // If the title to be deleted is larger than the root's title, it lies in right subtree
        else if (title.compareToIgnoreCase(root.notice.getTitle()) > 0)
            root.right = deleteRec(root.right, title);

        // If title is the same as root's title, then this is the node to be deleted
        else {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.notice = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.notice.getTitle());
        }

        return root;
    }

    private Notice minValue(BSTNode root) {
        Notice minValueNotice = root.notice;
        while (root.left != null) {
            minValueNotice = root.left.notice;
            root = root.left;
        }
        return minValueNotice;
    }
}
