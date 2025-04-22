package CampusCommunicator.notice;

// CampusCommunicator.notice.BSTNode.java

import CampusCommunicator.models.Notice;

public class BSTNode {
    Notice notice;
    BSTNode left, right;

    public BSTNode(Notice notice) {
        this.notice = notice;
        this.left = null;
        this.right = null;
    }
}
