package com.singlestone;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallListService {

    public List<CallListObj> convertContactsToCallListObj(List<PhoneNumber> phoneNumbers){
        return phoneNumbers.stream()
                .map(p-> new CallListObj(p.getContact().getName(),p.getNumber())).sorted(new Comparator<CallListObj>() {
                    @Override
                    public int compare(CallListObj o1, CallListObj o2) {
                        int result = o1.getName().getLast().compareToIgnoreCase(o2.getName().getLast());

                        if(result == 0)
                            return o1.getName().getFirst().compareToIgnoreCase(o2.getName().getFirst());
                        else
                            return result;
                    }
                }).collect(Collectors.toList());
}


}
