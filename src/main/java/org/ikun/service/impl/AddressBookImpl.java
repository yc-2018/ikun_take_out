package org.ikun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ikun.entity.AddressBook;
import org.ikun.mapper.AddressBookMapper;
import org.ikun.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
