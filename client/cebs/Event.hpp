#ifndef CEBS_EVENT_HPP
#define CEBS_EVENT_HPP

/**
 * An Event represents a significant change in the state of some object or
 * network entity or a request by one entity to another.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/

#ifndef STD_STRING
#include <string>
#define STD_STRING
#endif

namespace cebs
{
  using std::string;

  class ByteArray
  {
  public:
    char* dataBuf;
    int length_;
  }
  
  class Event
  {
  public:
    
    int getEventID() const
    {
      return getInteger(Event::EVENT_ID);
    }
	 
    void setEventID(const int eventID)
    {
      put(Event::ID, eventID);
    }
    
    long getSendTime() const;
    void setSendTime(const long sendTime);
    
    long getRecvTime() const;
    void setRecvTime(const long recvTime);
    
    int getSourceUID() const;
    void setSourceUID(const int sourceUID);
    
    const string& getTopic() const;
    void setTopic(const string& topic);
    
    const string& getSourceEventServer() const;
    void setSourceEventServer(const string& eventServer);
    
    virtual const bool containsKey(const string& key) const = 0;
    
    virtual void clear() = 0;
    
    virtual int getInteger(const string& key) const = 0;
    virtual long getLong(const string& key) const = 0;
    virtual double getDouble(const string& key) const = 0;
    virtual string& getString(const string& key) const = 0;
    virtual ByteArray& getBytes(const string& key) const = 0;
    
    virtual void put(const string& key, int val) = 0;
    virtual void put(const string& key, long val) = 0;
    virtual void put(const string& key, double val) = 0;
    virtual void put(const string& key, const string& val) = 0;
    virtual void put(const string& key, const ByteArray& val) = 0;
    
    virtual ~Event() {};
    
  private:
    /** constant name for the id of this event **/
    static const string EVENT_ID;
    
    /** constant name for when this event was sent **/
    static const string EVENT_SEND_TIMESTAMP;
    
    /** constant name for when this event was recieved **/
    static const string EVENT_RECV_TIMESTAMP;
    
    /** user id of the person who sent this event **/
    static const string EVENT_SOURCE_UID;
    
    /** the topic this event was published to **/
    static const string EVENT_TOPIC;
    
    /** the event server this event was sent to **/
    static const string EVENT_SOURCE_EVENT_SERVER;
  }
}
#endif /** CEBS_EVENT_HPP **/
