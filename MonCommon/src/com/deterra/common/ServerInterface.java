package com.deterra.common;

import java.rmi.*;

public interface ServerInterface extends Remote {

  public void registerForCallback(ClientInterface callbackClientObject, String passedName)
		throws java.rmi.RemoteException;

  public void unregisterForCallback(ClientInterface callbackClientObject)
        throws java.rmi.RemoteException;
  
  public String lobbyMsgUpdate(int players)
		throws java.rmi.RemoteException;

  public boolean requestMove(ClientInterface callbackObj, int x, int y, int oldx, int oldy)
		throws java.rmi.RemoteException;

  public byte[][] getServerBoard()
		throws java.rmi.RemoteException;

  public void killPlayer(ClientInterface callbackObj, int x, int y)
		throws java.rmi.RemoteException;
}