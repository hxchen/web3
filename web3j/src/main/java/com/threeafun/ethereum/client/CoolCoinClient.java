package com.threeafun.ethereum.client;

import com.threeafun.ethereum.contract.CoolCoin;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;


/**
 * Created by hxchen on 2018/4/21.
 */
public class CoolCoinClient{

    public static void main( String[] args ) throws Exception
    {
        Web3j web3j = Web3j.build(new HttpService("http://54.95.93.112:8545"));
        try {

            String account = web3j.ethAccounts().send().getAccounts().get(0);
            Credentials credentials = Credentials.create(account);
            //合约地址
            String address="0xd69A0599df3C0dd9ACAa0a9E47365730E9f4ee99";
            CoolCoin coolCoin = CoolCoin.load(address,web3j,credentials, Contract.GAS_PRICE,Contract.GAS_PRICE);
            //待查询余额的账户地址
            String account2 = "0xc2298C3398584aaB380fafb564037D9Fb910e0a1";
            RemoteCall remoteCall= coolCoin.balanceOf(account);
            System.out.println("返回结果:"+remoteCall.send());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
