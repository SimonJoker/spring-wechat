package com.meiben.wechat.common.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WxMpMessageRouter {

  private WxMpMessageRouterRule rule;
  private List<WxMpMessageRouterRule> rules
          = new ArrayList<WxMpMessageRouterRule>();

  public WxMpMessageRouter() {
  }

  public WxMpMessageRouter rule(){
    this.rule = new WxMpMessageRouterRule(this);
    return this;
  }

  public WxMpMessageRouter type(String type){
    this.rule.setMsgType(type);
    return this;
  }

  public WxMpMessageRouter event(String event){
    this.rule.setEvent(event);
    return this;
  }

  public WxMpMessageRouter content(String content){
    this.rule.setContent(content);
    return this;
  }

  public WxMpMessageRouter eventKey(String eventKey){
    this.rule.setEventKey(eventKey);
    return this;
  }

  public WxMpMessageRouter handler(IHandler handler){
    this.rule.handler(handler);
    return this;
  }

  public WxMpMessageRouter end(){
    this.rules.add(rule);
    this.rule = null;
    return this;
  }

  public String excute(Map<String, String> map){
    String msgType = map.get("MsgType");
    String content = map.get("Content");
    String message = null;
    for (int i=0;i<rules.size();i++){
      WxMpMessageRouterRule r = rules.get(i);
      if (r.getMsgType().equals(msgType)){
        System.out.println("type---:"+r.getMsgType());
        System.out.println("event---:"+msgType);
        if (r.getEvent() == null && r.getContent() == null){
          message = r.getHandlers().handlMessage(map);
          break;
        }else{
          if (r.getContent() != null && r.getContent().equals(content)){
            System.out.println("-----content----");
            message = r.getHandlers().handlMessage(map);
            break;
          }
          if (r.getEvent() != null && r.getEvent().equals(map.get("Event"))){
            if (r.getEventKey() != null && r.getEventKey().equals(map.get("EventKey"))){
              System.out.println("-----EventKey----");
              message = r.getHandlers().handlMessage(map);
              break;
            }
            message = r.getHandlers().handlMessage(map);
            break;
          }
        }
      }
    }
    return message;
  }

  public List<WxMpMessageRouterRule> getRules() {
    return rules;
  }

  public void setRules(List<WxMpMessageRouterRule> rules) {
    this.rules = rules;
  }
}
