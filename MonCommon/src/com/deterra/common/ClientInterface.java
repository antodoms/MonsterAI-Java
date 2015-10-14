package com.deterra.common;

public interface ClientInterface 
  extends java.rmi.Remote{

    public String notifyMe(String message) 
    		throws java.rmi.RemoteException;
    	  
	public void updateBoard(byte[][] serverboard)
			throws java.rmi.RemoteException;

	public void startGame()
			throws java.rmi.RemoteException;

	public void updateLobby(String message, String[] playerList)
			throws java.rmi.RemoteException;

	public void setPlayerPos(int i, int j)
		    throws java.rmi.RemoteException;

} // close interface