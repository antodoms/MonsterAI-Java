package com.deterra.common;



public interface ClientInterface 
  extends java.rmi.Remote{

    public String notifyMe(String message) 
      throws java.rmi.RemoteException;

    public byte[][] getBoardClient(byte[][] board)
    	throws java.rmi.RemoteException;
    
} // close interface