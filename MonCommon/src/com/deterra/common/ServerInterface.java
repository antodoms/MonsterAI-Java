package com.deterra.common;

import java.rmi.*;

public interface ServerInterface extends Remote {

  public String sayHello( )   
    throws java.rmi.RemoteException;


  public void registerForCallback(ClientInterface callbackClientObject
    ) throws java.rmi.RemoteException;

  public void unregisterForCallback(ClientInterface callbackClientObject)
    throws java.rmi.RemoteException;
  
  public byte[][] getClientBoard()
		    throws java.rmi.RemoteException;

  public String setupGame(int players)
		throws java.rmi.RemoteException;


  public void requestMove(ClientInterface callbackObj, int x, int y)
		throws java.rmi.RemoteException;
}