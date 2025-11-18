#import "DisplayControl.h"
#import <UIKit/UIKit.h>

@interface DisplayControl () {
    CGFloat _initialBrightness;
    BOOL _hasSavedInitial;
}
@end

@implementation DisplayControl

+ (NSString *)moduleName
{
    return @"DisplayControl";
}

- (instancetype)init
{
    if (self = [super init]) {
        _initialBrightness = [UIScreen mainScreen].brightness;
        _hasSavedInitial = YES;
    }
    return self;
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
(const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeDisplayControlSpecJSI>(params);
}

- (void)activate
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [UIApplication sharedApplication].idleTimerDisabled = YES;
    });
}

- (void)deactivate
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [UIApplication sharedApplication].idleTimerDisabled = NO;
    });
}

- (void)setBrightness:(double)value
{
    dispatch_async(dispatch_get_main_queue(), ^{
        CGFloat brightness = (CGFloat)value;
        if (brightness < 0.0) brightness = 0.0;
        if (brightness > 1.0) brightness = 1.0;
        
        [UIScreen mainScreen].brightness = brightness;
    });
}


- (void)resetBrightness
{
    if (!_hasSavedInitial) {
        return;
    }
    dispatch_async(dispatch_get_main_queue(), ^{
        [UIScreen mainScreen].brightness = self->_initialBrightness;
    });
}

@end
