package com.wallet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface WalletJpaRepository extends JpaRepository<WalletDto,Integer> {
//    WalletDto createWallet(WalletDto newWallet);
//    WalletDto getWalletById(Integer  walletId);
//    WalletDto updateWallet(WalletDto wallet);
//    WalletDto deleteWalletById(Integer walletId);
//    Collection<WalletDto> getAllWallets();
}
