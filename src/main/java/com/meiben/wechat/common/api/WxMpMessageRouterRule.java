package com.meiben.wechat.common.api;

public class WxMpMessageRouterRule {

  private final WxMpMessageRouter routerBuilder;

  private boolean async = true;

  private String fromUser;

  private String msgType;

  private String event;

  private String eventKey;

  private String content;

  private String rContent;

  private boolean reEnter = false;

  private IHandler handlers ;


  public WxMpMessageRouterRule(WxMpMessageRouter routerBuilder) {
    this.routerBuilder = routerBuilder;
  }

  /**
   * 设置是否异步执行，默认是true
   *
   * @param async
   * @return
   */
  public WxMpMessageRouterRule async(boolean async) {
    this.async = async;
    return this;
  }

  /**
   * 如果msgType等于某值
   *
   * @param msgType
   * @return
   */
  public WxMpMessageRouterRule msgType(String msgType) {
    this.msgType = msgType;
    return this;
  }

  /**
   * 如果event等于某值
   *
   * @param event
   * @return
   */
  public WxMpMessageRouterRule event(String event) {
    this.event = event;
    return this;
  }

  /**
   * 如果eventKey等于某值
   *
   * @param eventKey
   * @return
   */
  public WxMpMessageRouterRule eventKey(String eventKey) {
    this.eventKey = eventKey;
    return this;
  }

  /**
   * 如果content等于某值
   *
   * @param content
   * @return
   */
  public WxMpMessageRouterRule content(String content) {
    this.content = content;
    return this;
  }

  /**
   * 如果content匹配该正则表达式
   *
   * @param regex
   * @return
   */
  public WxMpMessageRouterRule rContent(String regex) {
    this.rContent = regex;
    return this;
  }

  /**
   * 如果fromUser等于某值
   *
   * @param fromUser
   * @return
   */
  public WxMpMessageRouterRule fromUser(String fromUser) {
    this.fromUser = fromUser;
    return this;
  }

  /**
   * 设置微信消息处理器
   *
   * @param handler
   * @return
   */
  public WxMpMessageRouterRule handler(IHandler handler) {
    this.handlers = handler;
    return this;
  }

  /**
   * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
   *
   * @return
   */
  public WxMpMessageRouter end() {
    this.routerBuilder.getRules().add(this);
    return this.routerBuilder;
  }

  /**
   * 规则结束，但是消息还会进入其他规则
   *
   * @return
   */
  public WxMpMessageRouter next() {
    this.reEnter = true;
    return end();
  }



  public WxMpMessageRouter getRouterBuilder() {
    return routerBuilder;
  }

  public boolean isAsync() {
    return async;
  }

  public void setAsync(boolean async) {
    this.async = async;
  }

  public String getFromUser() {
    return fromUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getEventKey() {
    return eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getrContent() {
    return rContent;
  }

  public void setrContent(String rContent) {
    this.rContent = rContent;
  }


  public boolean isReEnter() {
    return reEnter;
  }

  public void setReEnter(boolean reEnter) {
    this.reEnter = reEnter;
  }

  public IHandler getHandlers() {
    return handlers;
  }

  public void setHandlers(IHandler handlers) {
    this.handlers = handlers;
  }
}
