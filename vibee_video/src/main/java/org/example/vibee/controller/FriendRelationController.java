package org.example.vibee.controller;

import org.example.vibee.entity.FriendRelation;
import org.example.vibee.service.FriendRelationService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("friend")
public class FriendRelationController {

    @Autowired
    private FriendRelationService friendRelationService;

    /**
     * 添加好友
     */
    @PostMapping("/add")
    public ResponseData addFriend(@RequestBody FriendRelation friendRelation) {
        if (friendRelation.getUserId() == null || friendRelation.getFriendId() == null) {
            return ResponseData.missParam("用户ID或好友ID");
        }
        if (friendRelation.getUserId().equals(friendRelation.getFriendId())) {
            return ResponseData.fail(80001, "不能添加自己为好友");
        }
        int result = friendRelationService.addFriend(friendRelation);
        if (result == 0) {
            return ResponseData.fail(80002, "你们已经是好友了");
        }
        return ResponseData.success("添加好友成功");
    }

    /**
     * 删除好友
     */
    @DeleteMapping("/remove")
    public ResponseData removeFriend(@RequestParam Integer userId, @RequestParam Integer friendId) {
        friendRelationService.removeFriend(userId, friendId);
        return ResponseData.success("删除好友成功");
    }

    /**
     * 获取好友列表
     */
    @GetMapping("/list/{userId}")
    public ResponseData getFriends(@PathVariable Integer userId) {
        List<FriendRelation> friends = friendRelationService.getFriends(userId);
        return ResponseData.success(friends);
    }

    /**
     * 检查好友关系
     */
    @GetMapping("/check")
    public ResponseData checkFriendship(@RequestParam Integer userId, @RequestParam Integer friendId) {
        boolean isFriend = friendRelationService.isFriend(userId, friendId);
        return ResponseData.success(isFriend);
    }
} 