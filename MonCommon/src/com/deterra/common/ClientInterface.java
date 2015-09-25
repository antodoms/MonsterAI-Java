package com.deterra.common;

import java.rmi.RemoteException;

public interface ClientInterface 
  extends java.rmi.Remote{

    public String notifyMe(String message) 
    		throws java.rmi.RemoteException;

    public byte[][] printBoardClient(byte[][] board)
    		throws java.rmi.RemoteException;
    
    public void getBoardServer(byte[][] serverboard) 
    		throws java.rmi.RemoteException;
		  
	public void updateBoard(byte[][] serverboard)
			throws java.rmi.RemoteException;

	public void startGame()
			throws java.rmi.RemoteException;

	public void updateLobby(String message)
			throws java.rmi.RemoteException;

} // close interface