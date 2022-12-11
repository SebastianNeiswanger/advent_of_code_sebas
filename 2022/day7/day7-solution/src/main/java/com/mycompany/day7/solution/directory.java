package com.mycompany.day7.solution;

import java.util.Vector;

/**
 * Directory class that exists within a filesystem.
 * It works by assuming that it is within a tree that can grow infinitely.
 * You can add child directories and files to it. Both will incriment size.
 * You cannot move directories as it is not needed for this problem.
 */
public class directory {
    private directory parent = null;
    private String name;
    private int size;
    private Vector<directory> childDirectories = new Vector<directory>();
    private Vector<file> childFiles = new Vector<file>();

    public directory(String inputName) {
        name = inputName;
        size = 0;
    }

    public directory(directory inputParent, String inputName) {
        this(inputName);
        parent = inputParent;
    }

    /**
     * For moving up the directory tree
     * 
     * @return the parent directory pointer
     */
    public directory getParent() {
        return parent;
    }

    /**
     * Used to move the file system up or down
     * 
     * @param whereTo string of target directory. ".." will move up the fileSystem
     * @return the directory that was asked to move to. Null if the directory does not exist
     */
    public directory moveDirectory(String whereTo) {
        if (whereTo.equals("..")) {
            // moving up the filesystem
            return parent;
        } else {
            // moving down the filesystem
            directory target = null;
            for (int i = 0; i < childDirectories.size(); i++) {
                if (childDirectories.elementAt(i).getName().equals(whereTo)) {
                    target = childDirectories.elementAt(i);
                    break;
                }
            }
            return target;
        }
    }

    /**
     * Creates a new child directory with the name given,
     * and sets parent to current
     * 
     * @param name What the new directory is called
     * @return The new directory that was created, null if directory already exists as a child
     */
    public directory newChildDirectory(String name) {
        directory newChildDir = new directory(this, name);
        for (directory dir : childDirectories) {
            if (dir.getName().equals(newChildDir.getName())) {
                return null;
            }
        }
        childDirectories.add(newChildDir);
        return newChildDir;
    }

    /**
     * Creates a new child file with the name and size given,
     * and sets parent to current. This will also increase the size of the current directory, 
     * and parent directories
     * 
     * @param size The size of the new file
     * @param name What the new directory is called
     * @return The new file that was created, null if file already exists as a child
     * @see increaseSize
     */
    public file newChildFile(int size, String name) {
        file newChildFile = new file(size, name);
        for (file file : childFiles) {
            if (file.getName().equals(newChildFile.getName())) {
                return null;
            }
        }
        childFiles.add(newChildFile);
        increaseSize(newChildFile.getSize());
        return newChildFile;
    }

    /**
     * See current directory name
     * 
     * @return the name string of this directory
     */
    public String getName() {
        return name;
    }

    /**
     * See current directory size
     * 
     * @return the current size of the directory
     */
    public int getSize() {
        return size;
    }

    /**
     * This will increase the size of this direcotry
     * and parent directories by given amount
     * 
     * @param increaseBy increases directory size by this amount
    */
    public void increaseSize(int increaseBy) {
        size += increaseBy;
        if (parent != null) {
            parent.increaseSize(increaseBy);
        }
    }
}
