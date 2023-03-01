package com.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletJpaRepository walletRepository;

    @Autowired
    private WalletService wallet1Service;

    @Override
    public WalletDto registerWallet(WalletDto wallet) throws WalletException {
        return this.walletRepository.save(wallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> employeeOptional = this.walletRepository.findById(walletId);
        if(employeeOptional.isEmpty())
            throw new WalletException("Employee could not be found id:"+walletId);

        return employeeOptional.get();
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) throws WalletException {
        Optional<WalletDto> opt=this.walletRepository.findById(wallet.getId());
        if(opt.isEmpty())
            throw new WalletException("No Such Wallet account found with Id:"+wallet.getId());
        return this.walletRepository.save(wallet);

    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> opt=this.walletRepository.findById(walletId);
        if(opt.isEmpty())
            throw new WalletException("No Such Wallet account found with Id:"+walletId);
        WalletDto wallet=opt.get();
        this.walletRepository.delete(wallet);
    return wallet;
    }


    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
        WalletDto wallet = this.wallet1Service.getWalletById(walletId);
        if(wallet==null)
            throw new WalletException("Wallet does not exists to add funds, id:"+walletId);
        Double newBalance = wallet.getBalance()+amount;
        wallet.setBalance(newBalance);
        this.wallet1Service.updateWallet(wallet);
       return null;
    }

    @Override
    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletException {
        WalletDto wallet = this.wallet1Service.getWalletById(walletById);
        if(wallet==null)
            throw new WalletException("Wallet does not exists to withdraw, try using valid account id");
        Double balance= wallet.getBalance();
        if(balance<amount)
            throw new WalletException("Insufficient balance, current balance:"+balance);
        balance-=amount;
        wallet.setBalance(balance);
        this.wallet1Service.updateWallet(wallet);
       return null;
    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {

        WalletDto fromWallet = this.wallet1Service.getWalletById(fromWalletId);
        if(fromWallet == null)
            throw new WalletException("From wallet does not exists, id:"+fromWalletId);
        WalletDto toWallet = this.wallet1Service.getWalletById(toWalletId);
        if(toWallet== null)
            throw new WalletException("To wallet does not exists, id:"+toWalletId);
        Double fromBalance = fromWallet.getBalance();
        if(fromBalance<amount)
            throw new WalletException("Insufficient balance, current balance:"+fromBalance);

        fromWallet.setBalance(fromBalance-amount);

        Double toBalance = toWallet.getBalance();
        toWallet.setBalance(toBalance+amount);

        this.wallet1Service.updateWallet(fromWallet);
        this.wallet1Service.updateWallet(toWallet);
        return true;
    }

    @Override
    public Collection<WalletDto> getAllWallets() {
        return this.walletRepository.findAll();
    }
}
